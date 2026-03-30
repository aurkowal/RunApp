package pl.coderslab.runapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date; //data biegu
    private Long time; //czas trwania biegu w sekundach
    private Long distance; //dystans w metrach
    private double pace; // tempo biegu

    @ManyToOne
    private Runner runner;

    @ManyToOne
    private RunRoute runRoute;


}
