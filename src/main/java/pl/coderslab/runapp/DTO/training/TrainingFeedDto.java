package pl.coderslab.runapp.DTO.training;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingFeedDto {

    private long trainingId;
    private String runnerName;
    private LocalDate date;
    private double distance;
    private long time;
    private double pace;

}
