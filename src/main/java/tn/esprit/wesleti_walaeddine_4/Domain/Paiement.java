package tn.esprit.wesleti_walaeddine_4.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaiement;

    private BigDecimal montant;
    private LocalDate datePaiement;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    // Paiement * ---> 1 Contrat : côté propriétaire (FK contrat_id_contrat)
    @ManyToOne(fetch = FetchType.LAZY)
    private Contrat contrat;
}
