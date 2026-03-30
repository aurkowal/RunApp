package pl.coderslab.runapp.DTO.route;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestDto {

    private Long runnerId;
    private Long startLocationId;
    private Long endLocationId;
    private double plannedDistance;

}

