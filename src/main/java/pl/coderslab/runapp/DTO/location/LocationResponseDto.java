package pl.coderslab.runapp.DTO.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocationResponseDto {

    private Long id;
    private String placeName;
    private double latitude;
    private double longitude;

}
