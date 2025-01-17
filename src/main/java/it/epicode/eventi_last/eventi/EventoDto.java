package it.epicode.eventi_last.eventi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDto {
    @NotBlank(message = "Inserisci un nome")
    private String nome;
    @NotBlank(message = "Inserisci una descrizione")
    private String descrizione;
    @NotNull(message = "Inserisci una data")
    private LocalDate data;
    @NotBlank(message = "Inserisci un luogo")
    private String luogo;
    @NotNull(message = "inserisci un numero di posti")
    @PositiveOrZero(message = "il numero di posti deve essere inferiore o uguale a zero")
    private Integer posti;
}
