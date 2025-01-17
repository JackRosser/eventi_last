package it.epicode.eventi_last.user;

import it.epicode.eventi_last.eventi.Evento;
import it.epicode.eventi_last.eventi.EventoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneSvc {

    private final PrenotazioneRepo prenotazioneRepo;
    private final EventoRepo eventoRepo;
    private final UtenteRepo utenteRepo;

    public Prenotazione creaPrenotazione(Long eventoId, PrenotazioneDto prenotazioneDto) {
        Evento evento = eventoRepo.findById(eventoId).orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));
        Utente utente = utenteRepo.findById(prenotazioneDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        if (evento.getPosti() < prenotazioneDto.getPostiPrenotati()) {
            throw new IllegalArgumentException("Posti insufficienti");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazione.setPostiPrenotati(prenotazioneDto.getPostiPrenotati());

        evento.setPosti(evento.getPosti() - prenotazioneDto.getPostiPrenotati());
        eventoRepo.save(evento);

        return prenotazioneRepo.save(prenotazione);
    }

    public List<Prenotazione> trovaPrenotazioniPerUtente(Long userId) {
        Utente utente = utenteRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        return prenotazioneRepo.findByUtente(utente);
    }

    public void cancellaPrenotazione(Long prenotazioneId) {
        Prenotazione prenotazione = prenotazioneRepo.findById(prenotazioneId).orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        Evento evento = prenotazione.getEvento();
        evento.setPosti(evento.getPosti() + prenotazione.getPostiPrenotati());
        eventoRepo.save(evento);

        prenotazioneRepo.delete(prenotazione);

    }
}
