package it.epicode.eventi_last.eventi;

import it.epicode.eventi_last.exceptions.AlreadyExistsException;
import it.epicode.eventi_last.user.Utente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class EventoSvc {
    private final EventoRepo eventoRepo;

    // CREO UN EVENTO

    public Evento create(@Valid EventoDto request, Utente creator) {
        if (eventoRepo.existsByName(request.getName())) {
            throw new AlreadyExistsException("Evento già esistente");
        }

        Evento evento = new Evento();
        BeanUtils.copyProperties(request, evento);
        evento.setCreatedBy(creator); // Associa l'organizzatore

        return eventoRepo.save(evento);
    }


    // CERCO UN EVENTO DA ID

    public Evento findById(Long id) {
        if (!eventoRepo.existsById(id)) {
            throw new EntityNotFoundException("Evento non trovato");
        }
        return eventoRepo.findById(id).get();
    }

    // CERCO TUTTI GLI EVENTI

    public List<Evento> findAll() {
        return eventoRepo.findAll();
    }

    // MODIFICO EVENTO

    public Evento update(Long id, EventoDto request, Utente currentUser) {
        Evento evento = findById(id);

        // Verifica che l'utente sia il creatore dell'evento
        if (!evento.getCreatedBy().equals(currentUser)) {
            throw new SecurityException("Non sei autorizzato a modificare questo evento");
        }

        BeanUtils.copyProperties(request, evento);
        return eventoRepo.save(evento);
    }


    // ELIMINO EVENTO

    public String delete(Long id, Utente currentUser) {
        Evento evento = findById(id);

        // Verifica che l'utente sia il creatore dell'evento
        if (!evento.getCreatedBy().equals(currentUser)) {
            throw new SecurityException("Non sei autorizzato a eliminare questo evento");
        }

        eventoRepo.delete(evento);
        return "Evento eliminato con successo";
    }


}
