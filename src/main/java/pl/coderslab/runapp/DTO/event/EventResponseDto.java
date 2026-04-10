package pl.coderslab.runapp.DTO.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EventResponseDto {
    private Long id;
    private String name;
    private LocalDate date;
    private String city;
    private double distance;
}
