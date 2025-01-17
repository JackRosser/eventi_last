package it.epicode.eventi_last.eventi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepo extends JpaRepository<Evento, Long> {
    public Boolean existsByName(String name);

}
