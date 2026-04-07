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
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"follower_id", "followed_id"}
        )
)
public class Follow {

    public Follow(Runner follower, Runner followed) {
        this.follower = follower;
        this.followed = followed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Runner follower;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private Runner followed;
}
