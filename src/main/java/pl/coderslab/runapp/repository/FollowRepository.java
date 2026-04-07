package pl.coderslab.runapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.runapp.entity.Follow;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowedId(Long followedId);

    List<Follow> findByFollowerId(Long followerId);

    Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId);
}
