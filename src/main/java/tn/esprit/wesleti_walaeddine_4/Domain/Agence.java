package tn.esprit.wesleti_walaeddine_4.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // proxy LAZY : champs techniques Hibernate
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    // Agence 1 --- * Employe : côté inverse, aucune cascade
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Employe> employes = new ArrayList<>();

    // Agence 1 --- * Vehicule : côté inverse, aucune cascade (un véhicule survit à son agence)
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Vehicule> vehicules = new ArrayList<>();
}
