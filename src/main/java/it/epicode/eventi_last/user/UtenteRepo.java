package it.epicode.eventi_last.user;

import it.epicode.eventi_last.eventi.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepo extends JpaRepository<Utente, Long> {

}
