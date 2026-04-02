package pl.coderslab.runapp.DTO.route;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestDto {


    @NotNull(message = "Runner ID is required")
    private Long runnerId;


    @NotNull(message = "Start location is required")
    private Long startLocationId;


    @NotNull(message = "End location is required")
    private Long endLocationId;


    @Positive(message = "Planned distance must be positive")
    private double plannedDistance;

}

