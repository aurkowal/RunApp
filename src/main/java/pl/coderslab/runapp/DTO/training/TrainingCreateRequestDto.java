package pl.coderslab.runapp.DTO.training;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCreateRequestDto {

    @PastOrPresent
    private LocalDate date;

    @NotBlank
    @Min(1)
    private Long runRouteId;

    @NotBlank
    @Min(1)
    private Long time;

    @NotBlank
    @Min(1)
    private Long distance;

}
