package tn.esprit.wesleti_walaeddine_4.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    // Reservation * ---> 1 Vehicule : unidirectionnelle (FK vehicule_id_vehicule)
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicule vehicule;

    // Reservation * --- 1 Client : côté propriétaire (FK client_id_client)
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    // Reservation 1 --- 1 Contrat : côté inverse, cascade ALL
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Contrat contrat;
}
