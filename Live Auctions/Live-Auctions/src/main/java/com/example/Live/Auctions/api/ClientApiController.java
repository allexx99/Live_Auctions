package com.example.Live.Auctions.api;

import com.example.Live.Auctions.dto.ClientDTO;
import com.example.Live.Auctions.dto.PostDTO;
import com.example.Live.Auctions.dto.UserDTO;
import com.example.Live.Auctions.model.Client;
import com.example.Live.Auctions.model.Post;
import com.example.Live.Auctions.service.impl.ClientService;
import com.example.Live.Auctions.service.impl.EmailServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class ClientApiController {

    private final ClientService clientService;
    private final EmailServiceImpl emailService;

    @Autowired
    public ClientApiController(ClientService clientService, EmailServiceImpl emailService) {
        this.clientService = clientService;
        this.emailService = emailService;
    }

    @GetMapping(value = "/helloClient")
    public String Hello() {
        return "Hi there!";
    }

    // --------------------------------------------- saveClient ---------------------------------------------------- //

    // changed for DTO
    @Operation(summary = "Add client", description = "Add a new client to the DB")
    @PostMapping(value = "/saveClient")
    public String saveClient(@RequestBody @Valid ClientDTO clientDTO) {
        Client client = clientService.addClient(clientDTO);
        emailService.sendSimpleEmail(clientDTO.getEmail(), "Register Confirmation", "Thank for creating your account!");
        return "Saved...";
    }

    @GetMapping(value = "/getclientbyid/{clientId}")
    public Client getClientById(@PathVariable long clientId) {
        Client client = clientService.getClientById(clientId);
        // System.out.println("client: " + client.getFirstName());
        return client;
    }

    // --------------------------------------------- saveClient ---------------------------------------------------- //

    @Operation(summary = "Get clients", description = "Get all the clients from DB")
    @GetMapping(value = "/clients")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    // changed for DTO
    // this method IS NOT DONE!!
    @Operation(summary = "Update client", description = "Update client by id")
    @PostMapping(value = "/updateClient/{id}")
    public String updateClient(@PathVariable long id, @RequestBody ClientDTO clientDTO) {
        clientService.updateClient(id, clientDTO);
        return "Client updated...";
    }

    @Operation(summary = "Delete client", description = "Delete client by id")
    @DeleteMapping(value = "/deleteClient/{id}")
    public String deleteClient(@PathVariable long id) {
        clientService.deleteClient(id);
        return "Delete client with id " + id;
    }

    // --------------------------------------------- login ---------------------------------------------------- //

    /*@Operation(summary = "Login", description = "Checks if the data filled in the html form is correct, and if it's correct then the user is logged in")
    @PostMapping(value = "/login")
    public User login(@RequestBody User user) {
        if(clientService.login(user)) {
            return user;
        }
        return new User("", "");
    }*/

    @Operation(summary = "Login", description = "Checks if the data filled in the html form is correct, and if it's correct then the user is logged in")
    @PostMapping(value = "/login")
    public ClientDTO login(@RequestBody UserDTO userDTO) {
        ClientDTO clientDTO = clientService.login(userDTO);
        if(clientDTO != null) {
            System.out.println(clientDTO.getId());
            return clientDTO;
        }
        return new ClientDTO("", "");
    }

    // --------------------------------------------- login ---------------------------------------------------- //

    @PostMapping(value = "/logout/{clientId}")
    public String logout(@PathVariable long clientId) {
        clientService.logout(clientId);
        return "Logged out";
    }

    /*@Operation(summary = "Unused method")
    @PostMapping(value = "/loginId")
    public Long loginId(@RequestBody User user) {
        if(clientService.login(user)) {
            System.out.println("this user's id is: " + user.getId());
            return user.getId();
        }
        return Long.valueOf(0);
    }*/

    // --------------------------------------------- saveToMyPostList ---------------------------------------------------- //

    /*@Operation(summary = "Save item", description = "Add the new item to the user's list of objects and also to the DB")
    @PostMapping(value = "/saveToMyPosts/{username}")
    public String saveToMyPostList(@PathVariable String username, @RequestBody Post post) {
        clientService.saveToMyPostList(username, post);
        return "Post saved in your post list";
    }*/

    // "saveToMyPostList" changed for DTO
    @Operation(summary = "Save item", description = "Add the new item to the user's list of objects and also to the DB")
    @PostMapping(value = "/saveToMyPosts/{username}")
    public void saveToMyPostList(@PathVariable String username, @RequestBody PostDTO postDTO) {
        clientService.saveToMyPostList(username, postDTO);
        // return "Post saved in your post list";
    }

    // ----------------------------------------------- saveToMyPostList -------------------------------------------------- //

    @Operation(summary = "Save to Watchlist", description = "Saves the current item to the user's watchlist")
    @PostMapping(value = "/saveToMyWatchlist/{username}/{postId}")
    public String saveToMyWatchlist(@PathVariable String username, @PathVariable long postId) {
        if(clientService.saveToMyWatchlist(username, postId)) {
            return "Post saved in your watchlist";
        }
        return "Post already in your watchlist";
    }

    @Operation(summary = "Check in Watchlist", description = "Checks if the current item is in the user's watchlist")
    @PostMapping(value = "/checkInWatchlist/{username}/{postId}")
    public boolean checkInWatchlist(@PathVariable String username, @PathVariable long postId) {
        if(clientService.checkInWatchlist(username, postId)) {
            return true;
        }
        return false;
    }

    @Operation(summary = "Delete from Watchlist", description = "Deletes the current item from user's watchlist")
    @DeleteMapping(value = "/deleteFromWatchlist/{username}/{postId}")
    public String deleteFromWatchlist(@PathVariable String username, @PathVariable long postId) {
        if(clientService.deleteFromWatchlist(username, postId)) {
            return "Item deleted from your watchlist";
        }
        return "Item not in your watchlist";
    }

    /*@GetMapping(value = "/export/{clientId}")
    public ResponseEntity exportClientDetails(@PathVariable Long clientId, @RequestParam String fileType) {
        System.out.println("Id client for XML: " + clientId);
        return ResponseEntity.ok(clientService.exportClientDetails(clientId,fileType));
    }*/
    @GetMapping(value = "/export/{clientId}/{fileType}")
    public ResponseEntity exportClientDetails(@PathVariable Long clientId, @PathVariable String fileType) {
        // System.out.println("Id client for XML: " + clientId);
        return ResponseEntity.ok(clientService.exportClientDetails(clientId,fileType));
    }

    @GetMapping(value = "/onlineusers")
    public int onlineusers() {
        // System.out.println("Online users: " + clientService.onlineusers());
        return clientService.onlineusers();
    }

    @GetMapping(value = "/yourwatchlist/{clientId}")
    public List<Post> yourWatchlist(@PathVariable long clientId) {
        return clientService.yourWatchlist(clientId);
    }

    @GetMapping(value = "/ismypost/{clientId}/{postId}")
    public boolean isMyPost(@PathVariable long clientId, @PathVariable long postId) {
        // System.out.println("Checking client: " + clientId + " with post id: " + postId);
        return clientService.isMyPost(clientId, postId);
    }

    @DeleteMapping (value = "/deleteMyPost/{clientId}/{postId}")
    public String deleteMyPost(@PathVariable long clientId, @PathVariable long postId) {
        clientService.deleteMyPost(clientId, postId);
        return "The post with id " + postId + " is deleted";
    }
}
