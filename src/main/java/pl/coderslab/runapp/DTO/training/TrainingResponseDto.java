package pl.coderslab.runapp.DTO.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TrainingResponseDto {

    private Long id;
    private LocalDate date;
    private Long distance;
    private Long time;
    private double pace;

}
