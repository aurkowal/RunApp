package pl.coderslab.runapp.DTO.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowResponseDto {
    private Long runnerId;
    private String runnerName;
}
