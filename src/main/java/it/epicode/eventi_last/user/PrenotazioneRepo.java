package it.epicode.eventi_last.user;

import it.epicode.eventi_last.eventi.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtente(Utente utente);
}
