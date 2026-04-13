package pl.coderslab.runapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.runapp.DTO.follow.ActivityFeedDto;
import pl.coderslab.runapp.DTO.follow.FollowResponseDto;
import pl.coderslab.runapp.DTO.training.TrainingFeedDto;
import pl.coderslab.runapp.service.FollowService;

import java.util.List;

@RestController
public class FollowController {

    private final FollowService followService;
    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    // FOLLOW
    @PostMapping("/runners/{followerId}/follow/{followedId}")
    public void follow(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId ) {
        followService.follow(followerId, followedId);
    }

    // UNFOLLOW
    @DeleteMapping("/runners/{followerId}/follow/{followedId}")
    public void unfollow(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId) {
        followService.unfollow(followerId, followedId);
    }
    // OBSERWATORTZY RUNNERA
    @GetMapping("/runners/{runnerId}/followers")
    public List<FollowResponseDto> getFollowers(@PathVariable("runnerId") Long runnerId) {
        return followService.getFollowers(runnerId);
    }

    // OSOBY, KTORE OBSERWUJE RUNNER
    @GetMapping("/runners/{runnerId}/following")
    public List<FollowResponseDto> getFollowing(@PathVariable("runnerId") Long runnerId) {
        return followService.getFollowing(runnerId);
    }

    // FEED, czyli wszystkie treningi osob, ktore obserwuje runner o id
    @GetMapping("/runners/{runnerId}/feed-trainings")
    public List<TrainingFeedDto> getFeed(@PathVariable("runnerId") Long runnerId) {
        return followService.getFeed(runnerId);
    }

    @GetMapping("/runners/{runnerId}/feed-all-activity")
    public List<ActivityFeedDto> getAllActivity(@PathVariable("runnerId") Long runnerId) {
        return followService.getActivity(runnerId);
    }

}
