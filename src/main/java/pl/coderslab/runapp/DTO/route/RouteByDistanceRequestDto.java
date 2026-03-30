package pl.coderslab.runapp.DTO.route;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteByDistanceRequestDto {

    private Long runnerId;
    private Long startLocationId;
    private int targetDistance; // wygeneruje trase o zadanym dystansie, w metrach

}
