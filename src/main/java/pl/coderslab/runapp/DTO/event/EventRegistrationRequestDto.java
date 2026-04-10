package pl.coderslab.runapp.DTO.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventRegistrationRequestDto {

    private Long runnerId;
    private Long eventId;
}
