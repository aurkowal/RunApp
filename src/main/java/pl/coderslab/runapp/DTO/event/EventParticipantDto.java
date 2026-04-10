package pl.coderslab.runapp.DTO.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventParticipantDto {

    private Long runnerId;
    private String runnerName;

}
