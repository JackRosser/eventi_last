package it.epicode.eventi_last.user;

import it.epicode.eventi_last.auth.AppUser;
import it.epicode.eventi_last.eventi.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepo extends JpaRepository<Utente, Long> {
    Optional<AppUser> findByUsername(String username);

}
