package com.example.Live.Auctions.api;

import com.example.Live.Auctions.dto.PostDTO;
import com.example.Live.Auctions.model.Post;
import com.example.Live.Auctions.service.impl.ClientService;
import com.example.Live.Auctions.service.impl.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostApiController {

    private final PostService postService;
    private final ClientService clientService;

    @Autowired
    public PostApiController(PostService postService, ClientService clientService) {
        this.postService = postService;
        this.clientService = clientService;
    }

    // ----------------------------------------------- savePost -------------------------------------------------- //

    /*@Operation(summary = "Save post", description = "Save a post to the DB")
    @PostMapping(value = "/savePost")
    public long savePost(@RequestBody Post post) {
        postService.addPost(post);
        // return "Post saved...";
        return post.getId();
    }*/

    // "savePost" changed for DTO
    @Operation(summary = "Save post", description = "Save a post to the DB")
    @PostMapping(value = "/savePost")
    public long savePost(@RequestBody PostDTO postDTO) {
        postService.addPost(postDTO);
        // return "Post saved...";
        return postDTO.getId();
    }

    // ----------------------------------------------- savePost -------------------------------------------------- //

    @Operation(summary = "Get posts", description = "Get all the posts from the DB")
    @GetMapping(value = "/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @Operation(summary = "Get post by id", description = "Get a post from DB with a certain id")
    @GetMapping(value = "/posts/{id}")
    public Post getPostById(@PathVariable long id) {
        return postService.postById(id);
    }

    // ----------------------------------------------- updatePost -------------------------------------------------- //

    /*@Operation(summary = "Update post", description = "Update post by id")
    @PostMapping(value = "/updatePost/{id}")
    public String updatePost(@PathVariable long id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return "Post with id " + id + " ,updated...";
    }*/

    @Operation(summary = "Update post", description = "Update post by id")
    @PostMapping(value = "/updatePost/{id}")
    public String updatePost(@PathVariable long id, @RequestBody PostDTO postDTO) {
        postService.updatePost(id, postDTO);
        return "Post with id " + id + " ,updated...";
    }

    // ----------------------------------------------- updatePost -------------------------------------------------- //

    @Operation(summary = "Delete post", description = "Delete post from DB")
    @DeleteMapping(value = "/deletePost/{id}")
    public String deletePost(@PathVariable long id) {
        postService.deletePost(id);
        return "Delete post with id " + id;
    }

    @Operation(summary = "Update price", description = "Update the price of an object when someone places a new bid")
    @PostMapping(value = "/newPrice/{postId}/{price}")
    public String newPrice(@PathVariable long postId, @PathVariable int price) {
        if(postService.newPrice(postId, price)) {
            return "post with id " + postId + " has this price now " + price;
        }
        else {
            return "Your bid is too low.";
        }
    }

    @PostMapping(value = "/newTitle/{postId}/{newTitle}")
    public void newTitle(@PathVariable long postId, @PathVariable String newTitle) {
        postService.newTitle(postId, newTitle);
    }

    @PostMapping(value = "/newDescription/{postId}/{newDescription}")
    public void newDescription(@PathVariable long postId, @PathVariable String newDescription) {
        postService.newDescription(postId, newDescription);
    }

    @PostMapping(value = "/newCategory/{postId}/{newCategory}")
    public void newCategory(@PathVariable long postId, @PathVariable String newCategory) {
        postService.newCategory(postId, newCategory);
    }


}
