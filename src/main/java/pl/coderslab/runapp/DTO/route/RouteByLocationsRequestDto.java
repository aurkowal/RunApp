package pl.coderslab.runapp.DTO.route;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteByLocationsRequestDto {


    @NotNull(message = "Runner ID is required")
    Long runnerId;

    @NotNull(message = "Start is required")
    Long startLocationId;

    @NotNull(message = "End is required")
    Long endLocationId;

}
