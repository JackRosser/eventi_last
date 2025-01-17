package it.epicode.eventi_last.eventi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eventi")
@RequiredArgsConstructor
public class EventoController {
    private final EventoSvc eventoSvc;

    @PostMapping
    public ResponseEntity<Evento> create(@RequestBody EventoDto request) {
        return new ResponseEntity<>(eventoSvc.create(request), HttpStatus.CREATED);
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
    public ResponseEntity<Evento> update(@PathVariable Long id, @RequestBody EventoDto request) {
        return new ResponseEntity<>(eventoSvc.update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>(eventoSvc.delete(id), HttpStatus.OK);
    }

}

