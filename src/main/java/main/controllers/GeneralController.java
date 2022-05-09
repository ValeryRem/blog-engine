package main.controllers;

import main.view.response.InitResponse;
import main.view.requests.CommentRequest;
import main.view.requests.PostModerationRequest;
import main.view.service.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// искусственный коммит 9.5.22
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralController {
    private final InitResponse initResponse;
    private final GetService getService;
    private final  PostService postService;
    private final SettingsService settingsService;
    private final  UserService userService;

    public GeneralController(InitResponse initResponse, GetService getService, PostService postService,
                             SettingsService settingsService, UserService userService) {
        this.initResponse = initResponse;
        this.getService = getService;
        this.postService = postService;
        this.settingsService = settingsService;
        this.userService = userService;
    }

    @PutMapping("/settings")
    private ResponseEntity<?> putApiSettings (@RequestParam(defaultValue = "true") boolean multiuserMode,
                                             @RequestParam(defaultValue = "true") boolean postPremoderation,
                                             @RequestParam(defaultValue = "false") boolean statisticsInPublic) {
        return settingsService.putApiSettings (multiuserMode, postPremoderation, statisticsInPublic);
    }

    @GetMapping("/settings")
    private ResponseEntity<?> getApiSettings () {
        return settingsService.getApiSettings();
    }

    @GetMapping("/init")
    private InitResponse init() {
        return initResponse;
    }

    @GetMapping("/tag/{query}")
    private ResponseEntity<?> getTag (@PathVariable("query") String query) {
        return getService.getTag(query);
    }

    @GetMapping("/tag")
    private ResponseEntity<?> getTag () {
        return getService.getTag();
    }

    @GetMapping("/statistics/my")
    private ResponseEntity <?> getMyStatistics () {
        return getService.getMyStatistics();
    }

    @GetMapping("/statistics/all")
    private ResponseEntity <?> getAllStatistics () {
        return getService.getAllStatistics ();
    }

    @GetMapping("/calendar")
    private ResponseEntity <?> getApiCalendar (@RequestParam Integer year) {
        return getService.getApiCalendar (year);
    }

    @PostMapping("/moderation")
    private ResponseEntity<?> postApiModeration (@RequestBody PostModerationRequest postModerationRequest)
    {
        System.out.println("Method postApiModeration is activated.");
        return postService.postApiModeration(postModerationRequest.getPost_id(), postModerationRequest.getDecision());
    }

    @PostMapping("/comment")
    private ResponseEntity<?> postComment (@RequestBody CommentRequest commentRequest){
        System.out.println("Method postComment is activated.");
        return postService.postComment(commentRequest);
    }
}
