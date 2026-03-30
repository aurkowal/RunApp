package pl.coderslab.runapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunRoute {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double distance;   //metry, rzeczywisty z API
    private double duration;   //sekundy, czas podany z API, orientacyjny czas chodu

    private double plannedDistance;  // zadany przez usera


    @Column(columnDefinition = "TEXT")
    private String geometry;   //polyline z ORS


    @ManyToOne
    @JoinColumn(name = "runner_id")
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "start_location_id")
    private Location startLocation;

    @ManyToOne
    @JoinColumn(name = "end_location_id")
    private Location endLocation;




    public RunRoute(double distance, double duration, String geometry) {
        this.distance = distance;
        this.duration = duration;
        this.geometry = geometry;
    }

}
