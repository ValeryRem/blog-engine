package main.controllers;

import main.model.repository.PostRepository;
import main.model.repository.SessionRepository;
import main.model.repository.UserRepository;
import main.requests.*;
import main.service.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
// искусственный коммит 9.5.22
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PostController {
    private final GetService getService;
    private final PostService postService;
    private final AuthService authService;
    private final PostRepository postRepository;
    private final SessionRepository sessionRepository;
    private final HttpSession httpSession;
    private final UserRepository userRepository;

    public PostController(GetService getService, PostService postService, AuthService authService, PostRepository postRepository,
                          SessionRepository sessionRepository, HttpSession httpSession, UserRepository userRepository) {
        this.getService = getService;
        this.postService = postService;
        this.authService = authService;
        this.postRepository = postRepository;
        this.sessionRepository = sessionRepository;
        this.httpSession = httpSession;
        this.userRepository = userRepository;
    }

    @GetMapping("/post")
    @ResponseBody
    public ResponseEntity<?> getPosts (@RequestParam(defaultValue="0") Integer offset,
                                        @RequestParam(defaultValue="10") Integer limit,
                                        @RequestParam String mode){
        System.out.println("Method getPosts activated.");
        return getService.getPosts (offset, limit, mode);
    }

    @GetMapping("/post/{ID:\\d+}")
    public ResponseEntity<?> getPostById (@PathVariable("ID") Integer ID) {
        System.out.println("Method getPostById activated. ID requested: " + ID);
        return getService.getPostById(ID);
    }

    @GetMapping("/post/search")
    public ResponseEntity<?> getPostsBySearch (@RequestParam(defaultValue="0") Integer offset,
                                                @RequestParam(defaultValue="5") Integer limit,
                                                @RequestParam String query) {
        System.out.println("Method getPostsBySearch activated");
        return getService.getPostsBySearch(offset, limit, query);
    }

    @GetMapping("/post/byDate")
    public ResponseEntity<?> getPostsByDate (@RequestParam(defaultValue="0") Integer offset,
                                              @RequestParam(defaultValue="5") Integer limit,
                                              @RequestParam  String date){
        System.out.println("Method getPostsByDate activated by the date: " + date );
        return getService.getPostsByDate(offset, limit, date);
    }

    @GetMapping("/post/byTag")
    public ResponseEntity<?> getPostsByTag(@RequestParam(defaultValue="0") Integer offset,
                                            @RequestParam(defaultValue="12") Integer limit,
                                            @RequestParam String tag){
        System.out.println("Method getPostsByTag uses tag name:" + tag);
        return getService.getPostsByTag(offset, limit, tag);
    }

    @GetMapping("/post/moderation")
    public ResponseEntity<?> getPostsForModeration(@RequestParam(defaultValue="0") Integer offset,
                                                    @RequestParam(defaultValue="3") Integer limit,
                                                    @RequestParam(defaultValue="NEW") String status) {
        System.out.println("Method getPostsForModeration activated.");
        return getService.getPostsForModeration(offset, limit, status);
    }

    @GetMapping("/post/my")
    public ResponseEntity<?> getMyPosts (@RequestParam(defaultValue="0") Integer offset,
                                          @RequestParam(defaultValue="5") Integer limit) {
        System.out.println("Method getMyPosts activated.");
        return getService.getMyPosts(offset, limit);
    }

    @PostMapping("/post/like")
    public ResponseEntity<?> postLike (@RequestBody LikeRequest likeRequest) {
        System.out.println("Method postLike activated");
        System.out.println(likeRequest);
        return postService.postLikeDislike(likeRequest, 1);
    }

    @PostMapping("/post/dislike")
    public ResponseEntity<?> postDislike (@RequestBody LikeRequest likeRequest)
    {
        System.out.println("Method postDislike activated");
        System.out.println(likeRequest);
        return postService.postLikeDislike(likeRequest, -1);
    }

    @PostMapping("/post")
    public ResponseEntity<?> postPost (@RequestBody PostRequest postRequest) {
        System.out.println("Method postPost is activated");
        return postService.postPost(postRequest);
    }

    @PutMapping("/post/{ID:\\d+}")
    public ResponseEntity<?> putPost (@PathVariable(value = "ID") int ID, @RequestBody PutPostRequest putPostRequest){
        System.out.println("Method putPost is activated");
        return postService.putPost(ID, putPostRequest);
    }

    @PostMapping (value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ResponseEntity<?> postImage (@RequestBody MultipartFile image) throws IOException {
        return postService.postImage(image);
    }
}

