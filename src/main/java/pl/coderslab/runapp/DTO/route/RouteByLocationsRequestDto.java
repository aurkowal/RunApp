package pl.coderslab.runapp.DTO.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteByLocationsRequestDto {
    Long runnerId;
    Long startLocationId;
    Long endLocationId;

}
