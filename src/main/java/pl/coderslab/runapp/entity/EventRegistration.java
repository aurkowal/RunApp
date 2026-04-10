package pl.coderslab.runapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"runner_id", "event_id"}
        )
)
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "runner_id", nullable = false)
    private Runner runner;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    public EventRegistration(Runner runner, Event event) {
        this.event = event;
        this.runner = runner;
    }
}
