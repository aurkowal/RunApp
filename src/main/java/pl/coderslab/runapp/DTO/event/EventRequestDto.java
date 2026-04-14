package pl.coderslab.runapp.DTO.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EventRequestDto {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank
    @Size(min = 2, max = 30)
    private String city;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private double distance;
}
