package it.epicode.eventi_last.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PrenotazioneDto {
    @NotNull(message = "inserisci un user")
    private Long userId;
    @NotNull(message = "inserisci un numero di posti")
    @PositiveOrZero(message = "il numero di posti deve essere inferiore o uguale a zero")
    private Integer postiPrenotati;
}
