package it.epicode.eventi_last.eventi;

import it.epicode.eventi_last.user.Utente;
import it.epicode.eventi_last.user.UtenteRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/eventi")
@RequiredArgsConstructor
public class EventoController {
    private final EventoSvc eventoSvc;

    @Autowired
    private UtenteRepo utenteRepo; // Repository per accedere agli utenti

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody @Valid EventoDto request, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        // Trova l'utente autenticato nel database usando il nome utente dal Principal
        Utente creator = utenteRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + user.getUsername()));

        // Passa l'utente al servizio
        Evento evento = eventoSvc.create(request, creator);

        return new ResponseEntity<>(evento, HttpStatus.CREATED);
    }




    @GetMapping
    public ResponseEntity<List<Evento>> findAll() {
        return new ResponseEntity<>(eventoSvc.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> findById(@PathVariable Long id) {
        return new ResponseEntity<>(eventoSvc.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody EventoDto request, Principal principal) {
        // Recupera l'utente autenticato
        Utente currentUser = utenteRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + principal.getName()));

        // Passa l'utente autenticato al servizio
        return new ResponseEntity<>(eventoSvc.update(id, request, currentUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, Principal principal) {
        // Recupera l'utente autenticato
        Utente currentUser = utenteRepo.findByUsername(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + principal.getName()));

        // Passa l'utente autenticato al servizio
        return new ResponseEntity<>(eventoSvc.delete(id, currentUser), HttpStatus.OK);
    }


}

