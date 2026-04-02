package pl.coderslab.runapp.DTO.route;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteByDistanceRequestDto {


    @NotNull(message = "Runner ID is required")
    private Long runnerId;

    @NotNull(message = "Start location is required")
    private Long startLocationId;

    @Min(value = 500, message = "Minimal distance is 500 meters")
    private int targetDistance; // wygeneruje trase o zadanym dystansie, w metrach

}
