package pl.coderslab.runapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Runner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "runner")
    private List<RunRoute> runRoutes;

    @OneToMany(mappedBy = "runner")
    private List<Training> trainings;

    @OneToMany(mappedBy = "runner")
    private List<Location> locations;

    @OneToMany(mappedBy = "runner")
    private List<EventRegistration> registrations;

}
