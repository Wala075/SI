# Atelier 2 — Stratégie de fetch et de cascade (AutoLoc)

Règles appliquées à toutes les associations :

- **Fetch** : toutes les associations sont en `FetchType.LAZY`. Les `@ManyToOne` / `@OneToOne` (EAGER par défaut) sont forcées en LAZY pour ne pas charger toute la chaîne d'objets liés ; les `@OneToMany` / `@ManyToMany` (déjà LAZY) sont déclarées explicitement en LAZY.
- **Cascade** : une cascade n'est posée que lorsque l'enfant dépend du parent dans son cycle de vie. Elle est toujours déclarée sur le côté « parent » (côté inverse `mappedBy`), jamais sur le `@ManyToOne`.
- **Côté propriétaire** : toujours le côté qui porte la clé étrangère (`@ManyToOne`, ou `@OneToOne` sans `mappedBy`, ou `@ManyToMany` sans `mappedBy`).

## Tableau récapitulatif

| Association | Fetch | Cascade | Justification |
|---|---|---|---|
| Contrat → Paiement | LAZY | ALL + orphanRemoval | Un paiement n'existe que rattaché à son contrat (composition) ; supprimer le contrat supprime ses paiements, et retirer un paiement de la liste du contrat le supprime en base. |
| Agence → Vehicule | LAZY | Aucune | Un véhicule survit à son agence (il peut être réaffecté). Supprimer une agence ne doit jamais supprimer la flotte. |
| Agence → Employe | LAZY | Aucune | Un employé a son propre cycle de vie (mutation vers une autre agence) ; la liste des employés n'est utile que ponctuellement. |
| Vehicule ↔ Equipement | LAZY | Aucune | Les équipements sont partagés par plusieurs véhicules : supprimer un véhicule ne doit pas supprimer le GPS commun aux autres. `Set` utilisé pour éviter les doublons dans la table de jointure. |
| Client → Reservation | LAZY | PERSIST | Une réservation est créée dans le contexte de son client : enregistrer un nouveau client enregistre ses nouvelles réservations. Pas de REMOVE : l'historique des réservations doit être conservé. |
| Reservation → Vehicule | LAZY | Aucune | Le véhicule est une ressource indépendante, simplement référencée par la réservation. Association unidirectionnelle (pas de liste `reservations` dans `Vehicule`). |
| Reservation ↔ Contrat | LAZY | ALL (côté Reservation) | Le contrat est généré à partir de la réservation et n'a pas de sens sans elle : il suit son cycle de vie (création, mise à jour, suppression). |
| Vehicule → Maintenance | LAZY | PERSIST | Une opération de maintenance est enregistrée pour un véhicule : sauvegarder le véhicule enregistre ses nouvelles maintenances. Pas de REMOVE : l'historique de maintenance est conservé. |

## Détail du mapping et schéma généré

Schéma vérifié sur MySQL 8 (`ddl-auto=create`), noms de colonnes générés par Hibernate (aucun `@JoinColumn`) :

| Association | Côté propriétaire (attribut) | Côté inverse (`mappedBy`) | Clé étrangère / table générée |
|---|---|---|---|
| Contrat → Paiement | `Paiement.contrat` (`@ManyToOne`) | `Contrat.paiements` | `paiement.contrat_id_contrat` |
| Agence → Vehicule | `Vehicule.agence` (`@ManyToOne`) | `Agence.vehicules` | `vehicule.agence_id_agence` |
| Agence → Employe | `Employe.agence` (`@ManyToOne`) | `Agence.employes` | `employe.agence_id_agence` |
| Vehicule ↔ Equipement | `Vehicule.equipements` (`@ManyToMany` + `@JoinTable`) | `Equipement.vehicules` | table `vehicule_equipement` (`vehicules_id_vehicule`, `equipements_id_equipement`, clé primaire composite) |
| Client → Reservation | `Reservation.client` (`@ManyToOne`) | `Client.reservations` | `reservation.client_id_client` |
| Reservation → Vehicule | `Reservation.vehicule` (`@ManyToOne`) | — (unidirectionnelle) | `reservation.vehicule_id_vehicule` |
| Reservation ↔ Contrat | `Contrat.reservation` (`@OneToOne`) | `Reservation.contrat` | `contrat.reservation_id_reservation` (+ contrainte `UNIQUE`) |
| Vehicule → Maintenance | `Maintenance.vehicule` (`@ManyToOne`) | `Vehicule.maintenances` | `maintenance.vehicule_id_vehicule` |

## Comportements constatés (test sur MySQL)

- `persist(contrat)` avec 2 paiements dans sa liste → les 2 paiements sont insérés (cascade PERSIST via ALL).
- `find(Contrat)` → la liste `paiements` n'est pas chargée ; elle l'est au premier appel à `getPaiements()` (LAZY).
- `contrat.removePaiement(p)` → le paiement est supprimé en base sans supprimer le contrat (orphanRemoval).
- `remove(contrat)` → tous ses paiements sont supprimés (cascade REMOVE via ALL).
- Accès à `getPaiements()` hors transaction → `LazyInitializationException` (à traiter par une requête dédiée, Atelier 7).
- `persist(client)` → réservation, contrat et paiement insérés en chaîne (PERSIST → ALL → ALL).
- `remove(agence)` avec un véhicule rattaché → refusé par la clé étrangère, véhicule et employé intacts (aucune cascade).

## Points d'attention

- **LAZY vs EAGER** : EAGER sur `Contrat.paiements` chargerait les paiements à chaque lecture d'un contrat, même quand ils ne sont pas utilisés. LAZY garde la lecture d'un contrat légère ; c'est la configuration retenue.
- **`@OneToOne` côté inverse** : `Reservation.contrat` est déclaré LAZY mais Hibernate le charge quand même (requête supplémentaire), car il doit savoir si le contrat existe pour renvoyer `null` ou un objet. Le LAZY n'est réellement effectif que côté propriétaire (`Contrat.reservation`), sauf activation du bytecode enhancement.
- **Lombok ciblé** : `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`. Pas de `@Data` ni de `@ToString` : le `toString()`/`hashCode()` généré parcourrait les deux côtés des associations (StackOverflowError) et déclencherait le chargement des proxys LAZY.
- **Collections initialisées** (`new ArrayList<>()`, `new HashSet<>()`) avec `@Builder.Default` pour que le builder ne les laisse pas à `null`.
- **Méthodes utilitaires** `Contrat.addPaiement()` / `removePaiement()` : maintiennent les deux côtés de l'association synchronisés (seul le côté propriétaire `Paiement.contrat` est écrit en base).
