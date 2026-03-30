package pl.coderslab.runapp.DTO;


import lombok.Data;

@Data
public class RouteRequestDto {
    private double startLat;
    private double startLon;
    private double endLat;
    private double endLon;
}

