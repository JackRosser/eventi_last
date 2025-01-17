package it.epicode.eventi_last.user;

import it.epicode.eventi_last.auth.Role;
import it.epicode.eventi_last.eventi.Evento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role ruolo;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;
}
