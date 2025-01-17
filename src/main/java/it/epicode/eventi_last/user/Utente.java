package it.epicode.eventi_last.user;

import it.epicode.eventi_last.auth.Role;
import it.epicode.eventi_last.eventi.Evento;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;
}
