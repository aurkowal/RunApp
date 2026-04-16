package pl.coderslab.runapp.DTO.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventRegistrationRequestDto {

    @NotNull
    private Long runnerId;

    @NotNull
    private Long eventId;
}
