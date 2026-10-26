package tn.esprit.wesleti_walaeddine_4.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // proxy LAZY : champs techniques Hibernate
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;

    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private boolean valide;

    // Contrat 1 --- 1 Reservation : côté propriétaire (FK reservation_id_reservation)
    @OneToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    // Contrat 1 <>--- * Paiement : côté inverse, composition => cascade ALL + orphanRemoval
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Paiement> paiements = new ArrayList<>();

    // Méthodes utilitaires : garder les deux côtés de l'association synchronisés
    public void addPaiement(Paiement paiement) {
        paiements.add(paiement);
        paiement.setContrat(this);
    }

    public void removePaiement(Paiement paiement) {
        paiements.remove(paiement);
        paiement.setContrat(null);
    }
}
