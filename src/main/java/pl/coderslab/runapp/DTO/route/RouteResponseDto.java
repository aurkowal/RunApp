package pl.coderslab.runapp.DTO.route;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RouteResponseDto {
    private Long id;
    private double distance;
    private double duration;

}
