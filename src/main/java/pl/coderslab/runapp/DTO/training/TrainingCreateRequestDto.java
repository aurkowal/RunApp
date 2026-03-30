package pl.coderslab.runapp.DTO.training;

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

    private LocalDate date;
    private Long runRouteId;
    private Long time;
    private Long distance;

}
