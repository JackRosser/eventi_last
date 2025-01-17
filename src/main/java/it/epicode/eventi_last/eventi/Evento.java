package it.epicode.eventi_last.eventi;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private Integer posti;
}
