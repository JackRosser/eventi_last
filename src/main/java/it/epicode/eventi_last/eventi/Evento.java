package it.epicode.eventi_last.eventi;

import it.epicode.eventi_last.user.Prenotazione;
import it.epicode.eventi_last.user.Utente;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String descrizione;
    private String luogo;
    private Integer posti;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Utente createdBy; // Nuovo campo per tracciare l'organizzatore

    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;
}
