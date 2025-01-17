package it.epicode.eventi_last.eventi;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDto {
    @NotEmpty(message = "Il nome dell'evento è obbligatorio")
    private String name;

    @NotEmpty(message = "La descrizione dell'evento è obbligatoria")
    private String descrizione;

    @NotEmpty(message = "Il luogo dell'evento è obbligatorio")
    private String luogo;

    @NotNull(message = "Il numero di posti è obbligatorio")
    @Min(value = 1, message = "Il numero di posti deve essere almeno 1")
    private Integer posti;
}
