package pl.coderslab.runapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.runapp.DTO.follow.ActivityFeedDto;
import pl.coderslab.runapp.DTO.follow.FollowResponseDto;
import pl.coderslab.runapp.DTO.training.TrainingFeedDto;
import pl.coderslab.runapp.entity.Follow;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final RunnerRepository runnerRepository;
    private final TrainingRepository trainingRepository;
    private final EventRegistrationRepository eventRegistrationRepository;

    public FollowService(FollowRepository followRepository, RunnerRepository runnerRepository, TrainingRepository trainingRepository, EventRepository eventRepository, EventRegistrationRepository eventRegistrationRepository) {
        this.followRepository = followRepository;
        this.runnerRepository = runnerRepository;
        this.trainingRepository = trainingRepository;
        this.eventRegistrationRepository = eventRegistrationRepository;
    }

    public void follow(Long followerId, Long followedId) {

        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("Runner can not follow themselves");
        }

        //gdy juz jest follow
        if (followRepository.findByFollowerIdAndFollowedId(followerId, followedId).isPresent()) {
            return;
        }

        Runner follower = runnerRepository.findById(followerId).orElseThrow(() -> new EntityNotFoundException("Follower not found"));
        Runner followed = runnerRepository.findById(followedId).orElseThrow(() -> new EntityNotFoundException("Followed not found"));

        Follow follow = new Follow(follower, followed);
        followRepository.save(follow);

    }

    public void unfollow(Long followerId, Long followedId) {
        followRepository
                .findByFollowerIdAndFollowedId(followerId, followedId)
                .ifPresent(followRepository::delete);
    }

    public List<FollowResponseDto> getFollowers(Long runnerId) {
        return followRepository.findByFollowedId(runnerId).stream()
                .map(follow -> new FollowResponseDto(
                        follow.getFollower().getId(),
                        follow.getFollower().getName()
                )).toList();
    }

    public List<FollowResponseDto> getFollowing(Long runnerId) {
        return followRepository.findByFollowerId(runnerId).stream()
                .map(follow -> new FollowResponseDto(
                        follow.getFollowed().getId(),
                        follow.getFollowed().getName()
                )).toList();
    }

    public List<TrainingFeedDto> getFeed(Long runnerId) {
        //lista osob ktore obserwuje runner
        List<Long> followedIds = followRepository.findByFollowerId(runnerId)
                .stream().map(follow -> follow.getFollowed().getId()).toList();

        if (followedIds.isEmpty()) {
            return List.of();
        }

        //treningi obserwujacych po dacie
        return trainingRepository.findByRunnerIdInOrderByDateDesc(followedIds)
                .stream().map(
                        training -> new TrainingFeedDto(
                                training.getId(),
                                training.getRunner().getName(),
                                training.getDate(),
                                training.getDistance(),
                                training.getTime(),
                                training.getPace()
                        )
                ).toList();
    }

    public List<ActivityFeedDto> getActivity(Long runnerId) {
        //lista osob ktore obserwuje runner
        List<Long> followedIds = followRepository.findByFollowerId(runnerId)
                .stream().map(follow -> follow.getFollowed().getId()).toList();


        // lista ze wszystkimi aktywnosciami na feed
        List<ActivityFeedDto> activities = new ArrayList<>();

        // treningi
        activities.addAll(trainingRepository.findByRunnerIdInOrderByDateDesc(followedIds).stream()
                .map(training -> new ActivityFeedDto(
                        "Training",
                        training.getRunner().getName() + " przebiegł/a " + training.getDistance()/1000 + " km tempem "+ training.getPace() + "min/km",
                        training.getDate().atStartOfDay()
                )).toList());

        // eventy
        activities.addAll(eventRegistrationRepository.findByRunnerIdIn(followedIds).stream()
                .map(registration -> new ActivityFeedDto(
                        "Event",
                        registration.getRunner().getName() + " zapisał/a się na " + registration.getEvent().getName(),
                        registration.getEvent().getDate().atStartOfDay()
                )).toList());

        // followersi
        activities.addAll(followRepository.findByFollowedId(runnerId).stream()
                .map(follow -> new ActivityFeedDto(
                        "Follow",
                        follow.getFollower().getName() + " zaczął/ęła Cię obserwować",
                        LocalDateTime.now()
                )).toList());


        return activities.stream().sorted(Comparator.comparing(ActivityFeedDto::getDateTime).reversed()).toList();
    }


}
