package pl.coderslab.runapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RunRoute {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double distance;   // metry
    private double duration;   // sekundy

    @Column(columnDefinition = "TEXT")
    private String geometry;   // polyline z ORS


    public RunRoute() {}

    public RunRoute(double distance, double duration, String geometry) {
        this.distance = distance;
        this.duration = duration;
        this.geometry = geometry;
    }

}
