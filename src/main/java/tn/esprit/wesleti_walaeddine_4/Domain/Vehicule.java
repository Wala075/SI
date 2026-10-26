package tn.esprit.wesleti_walaeddine_4.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // proxy LAZY : champs techniques Hibernate
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    private String immatriculation;
    private String marque;
    private String modele;

    @Enumerated(EnumType.STRING)
    private CategorieVehicule categorie;

    private BigDecimal tarifJournalier;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    // Vehicule * --- 1 Agence : côté propriétaire (FK agence_id_agence)
    @ManyToOne(fetch = FetchType.LAZY)
    private Agence agence;

    // Vehicule * --- * Equipement : côté propriétaire (table de jointure vehicule_equipement)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vehicule_equipement")
    @Builder.Default
    private Set<Equipement> equipements = new HashSet<>();

    // Vehicule 1 --- * Maintenance : côté inverse, cascade PERSIST
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Maintenance> maintenances = new ArrayList<>();

    // Reservation ---> Vehicule : unidirectionnelle, rien de ce côté
}
