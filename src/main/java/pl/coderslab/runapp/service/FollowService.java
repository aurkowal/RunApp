package pl.coderslab.runapp.service;

import org.springframework.stereotype.Service;
import pl.coderslab.runapp.DTO.follow.FollowResponseDto;
import pl.coderslab.runapp.DTO.training.TrainingFeedDto;
import pl.coderslab.runapp.entity.Follow;
import pl.coderslab.runapp.entity.Runner;
import pl.coderslab.runapp.repository.FollowRepository;
import pl.coderslab.runapp.repository.RunnerRepository;
import pl.coderslab.runapp.repository.TrainingRepository;

import java.util.List;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final RunnerRepository runnerRepository;
    private final TrainingRepository trainingRepository;

    public FollowService(FollowRepository followRepository, RunnerRepository runnerRepository, TrainingRepository trainingRepository) {
        this.followRepository = followRepository;
        this.runnerRepository = runnerRepository;
        this.trainingRepository = trainingRepository;
    }

    public void follow(Long followerId, Long followedId) {

        if (followerId.equals(followedId)) {
            throw new IllegalArgumentException("Runner can not follow themselves");
        }

        //gdy juz jest follow
        if (followRepository.findByFollowerIdAndFollowedId(followerId, followedId).isPresent()) {
            return;
        }

        Runner follower = runnerRepository.findById(followerId).get();
        Runner followed = runnerRepository.findById(followedId).get();

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


}
