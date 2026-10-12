package tn.esprit.wesleti_walaeddine_4.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private RoleEmploye role;

    // Employe * --- 1 Agence : côté propriétaire (FK agence_id_agence)
    @ManyToOne(fetch = FetchType.LAZY)
    private Agence agence;
}
