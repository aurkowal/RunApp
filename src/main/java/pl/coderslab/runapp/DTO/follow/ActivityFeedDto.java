package pl.coderslab.runapp.DTO.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ActivityFeedDto {
    private String type;
    private String message;
    private LocalDateTime dateTime;

}
