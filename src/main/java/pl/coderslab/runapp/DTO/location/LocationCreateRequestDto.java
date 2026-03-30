package pl.coderslab.runapp.DTO.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCreateRequestDto {

    private String placeName;
    private double latitude;
    private double longitude;

}
