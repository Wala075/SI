package tn.esprit.wesleti_walaeddine_4.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numPermis;
    private LocalDate dateInscription;

    // Client 1 --- * Reservation : côté inverse, cascade PERSIST
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();
}
