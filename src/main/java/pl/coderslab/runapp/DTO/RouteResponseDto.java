package pl.coderslab.runapp.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RouteResponseDto {
    private Long id;
    private double distance;
    private double duration;


    public RouteResponseDto(Long id, double distance, double duration) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
    }

}
