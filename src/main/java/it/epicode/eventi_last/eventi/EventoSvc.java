package it.epicode.eventi_last.eventi;

import it.epicode.eventi_last.auth.AppUser;
import it.epicode.eventi_last.exceptions.AlreadyExistsException;
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

    public Evento create(@Valid EventoDto request, AppUser creator) {
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

    public Evento update(Long id, EventoDto request) {
        Evento e = findById(id);

        BeanUtils.copyProperties(request, e);
        return eventoRepo.save(e);
    }

    // ELIMINO EVENTO

    public String delete(Long id) {
        Evento e = findById(id);
        eventoRepo.delete(e);
        return "Employee with id " + id + " deleted";
    }

}
