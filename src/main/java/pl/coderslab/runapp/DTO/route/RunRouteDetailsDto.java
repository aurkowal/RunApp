package pl.coderslab.runapp.DTO.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RunRouteDetailsDto {
    private Long id;
    private String geometry;
    private double distance;
}
