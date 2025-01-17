package it.epicode.eventi_last.user;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {
    private final PrenotazioneSvc prenotazioneSvc;

    @Operation(summary = "Crea una nuova prenotazione", description = "Permette di creare una prenotazione per un evento.")
    @PostMapping("/evento/{eventoId}")
    public ResponseEntity<Prenotazione> creaPrenotazione(@PathVariable Long eventoId, @RequestBody PrenotazioneDto prenotazioneDto) {
        return new ResponseEntity<>(prenotazioneSvc.creaPrenotazione(eventoId, prenotazioneDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Trova prenotazioni per utente", description = "Recupera tutte le prenotazioni effettuate da un utente specifico.")
    @GetMapping("/utente/{userId}")
    public ResponseEntity<List<Prenotazione>> trovaPrenotazioniPerUtente(@PathVariable Long userId) {
        return new ResponseEntity<>(prenotazioneSvc.trovaPrenotazioniPerUtente(userId), HttpStatus.OK);
    }

    @Operation(summary = "Cancella una prenotazione", description = "Permette di annullare una prenotazione esistente.")
    @DeleteMapping("/{prenotazioneId}")
    public ResponseEntity<Void> cancellaPrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneSvc.cancellaPrenotazione(prenotazioneId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
