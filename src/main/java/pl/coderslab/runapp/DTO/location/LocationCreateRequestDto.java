package pl.coderslab.runapp.DTO.location;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateRequestDto {

    @NotBlank
    @Size(min = 2, max = 40)
    private String placeName;


    @NotNull
    @DecimalMin(value = "-90.0", message = "Szerokość geograficzna min. -90")
    @DecimalMax(value = "90.0", message = "Szerokość geograficzna max. 90")
    private double latitude;


    @NotNull
    @DecimalMin(value = "-180.0", message = "Długość geograficzna min. -180")
    @DecimalMax(value = "180.0", message = "Długość geograficzna max. 180")
    private double longitude;

}
