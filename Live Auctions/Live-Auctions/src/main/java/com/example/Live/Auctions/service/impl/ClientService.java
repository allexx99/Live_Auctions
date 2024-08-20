package com.example.Live.Auctions.service.impl;

import com.example.Live.Auctions.config.NotificationEndpoints;
import com.example.Live.Auctions.dao.ClientDao;
import com.example.Live.Auctions.dao.PostDao;
import com.example.Live.Auctions.dto.ClientDTO;
import com.example.Live.Auctions.dto.PostDTO;
import com.example.Live.Auctions.dto.UserDTO;
import com.example.Live.Auctions.exporter.FileExporter;
import com.example.Live.Auctions.exporter.FileType;
import com.example.Live.Auctions.exporter.TXTFileExporter;
import com.example.Live.Auctions.exporter.XMLFileExporter;
import com.example.Live.Auctions.model.Client;
import com.example.Live.Auctions.model.Post;
import com.example.Live.Auctions.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class ClientService implements ClientServiceInterface {

    private final ClientDao clientDao;
    private final PostDao postDao;
    private final SimpMessagingTemplate template;

    // here we are injecting into the actual constructor
    @Autowired
    public ClientService(ClientDao clientDao, PostDao postDao, SimpMessagingTemplate template) {
        this.clientDao = clientDao;
        this.postDao = postDao;
        this.template = template;
    }

    // changed for DTO
    public Client addClient(ClientDTO clientDTO) {
        // Generate a random salt
        // A salt is a random value used to add complexity to the hashing process.
        // It helps defend against attacks like rainbow tables. Generate a new random salt for each password.
        String salt = BCrypt.gensalt();
        // Hash the password with the salt
        String hashedPassword = BCrypt.hashpw(clientDTO.getPassword(), salt);
        clientDTO.setPassword(hashedPassword);

        Client client = clientDTO.convertToModel(clientDTO);
        return clientDao.save(client);
    }

    public List<Client> getClients() {
        return clientDao.findAll();
    }

    public Client getClientById(long clientId) {
        Client client = clientDao.findClientById(clientId);
        // System.out.println("In getClientById function");
        /*for(Post post : client.getPosts()) {
            System.out.println(post.getTitle());
        }*/
        // return client.getPosts();
        return clientDao.findClientById(clientId);
    }

    //changed for DTO
    //this function IS NOT DONE!!
    public void updateClient(long id, ClientDTO clientDTO) {
       Client client = clientDao.findClientById(id);
       client.setFirstName(clientDTO.getFirstName());
       client.setLastName(clientDTO.getLastName());
       client.setUsername(clientDTO.getUsername());
       client.setPassword(clientDTO.getPassword());
       /*client.setPostList(clientDTO.getPostDTOList());
       client.setPosts(clientDTO.getPosts());*/
       client.setEmail(clientDTO.getEmail());

       clientDao.save(client);
    }

    // public void updateWatchList(long id, )

    public void deleteClient(long id) {
        Client client = clientDao.findById(id).get();
        clientDao.delete(client);
    }

    // --------------------------------------------- login ---------------------------------------------------- //

    /*public boolean login(User user) {
        System.out.println(user.getUsername());
        List<Client> users = clientDao.findAll();
        for (Client client : users) {
            if (client.getUsername().equals(user.getUsername())) {
                if (client.getPassword().equals(user.getPassword()))
                    return true;
            }
        }
        return false;
    }*/

    // "login" changed for DTO
    public ClientDTO login(UserDTO userDTO) {


        System.out.println(userDTO.getUsername());
        List<Client> users = clientDao.findAll();
        for (Client client : users) {
            if (client.getUsername().equals(userDTO.getUsername())) {
                // if (client.getPassword().equals(userDTO.getPassword())) {
                if(BCrypt.checkpw(userDTO.getPassword(), client.getPassword())) {
                    client.setLoggedIn(true);
                    clientDao.save(client);
                    System.out.println(client.getId());
                    System.out.println(client.getUsername());
                    return client.convertToDTO(client);
                }
            }
        }
        return null;
    }

    // --------------------------------------------- login ---------------------------------------------------- //

    public void logout(long clientId) {
        Client client = findClientById(clientId);
        client.setLoggedIn(false);
        clientDao.save(client);
    }

    public Client findClientById(long id) {
        return clientDao.findById(id).get();
    }

    public Client findClientByUsername(String username) {
        Client client = clientDao.findClientByUsername(username);
        return client;
    }

    // --------------------------------------------- saveToMyPostList ---------------------------------------------------- //

    /*public void saveToMyPostList(String username, Post post) {
        Client client = findClientByUsername(username);
        System.out.println("username: " + client.getUsername());
        System.out.println("password: " + client.getPassword());
        System.out.println("this client has id " + client.getId());
        client.getPostList().add(post);
        System.out.println("The client with id " + client.getId() + " posted these objects:");
        for (Post post1 : client.getPostList()) {
            System.out.println(post1.getTitle());
        }
        clientDao.save(client);
    }*/

    // "saveToMyPostList" changed for DTO
    public void saveToMyPostList(String username, PostDTO postDTO) {
        Client client = findClientByUsername(username);
        Post postModel = postDTO.convertToModel(postDTO);
        System.out.println("username: " + client.getUsername());
        System.out.println("password: " + client.getPassword());
        System.out.println("this client has id " + client.getId());
        client.getPostList().add(postModel);
        System.out.println("The client with id " + client.getId() + " posted these objects:");
        for (Post post1 : client.getPostList()) {
            System.out.println(post1.getTitle());
        }
        clientDao.save(client);

        this.template.convertAndSend(NotificationEndpoints.NEW_POST_ADDED,
                "New post was added with the title: " + postModel.getTitle());

        // NotificationEndpoints.sendNotificationToAll("New post was added with the title: " + postModel.getTitle());
    }

    // --------------------------------------------- saveToMyPostList ---------------------------------------------------- //

    public Post findPostById(long id) {
        return postDao.findPostById(id);
    }

    public boolean saveToMyWatchlist(String username, long id) {
        Client client = findClientByUsername(username);
        Post post = findPostById(id);
        // System.out.println("The post " + post.getTitle() + " was posted by " + username);
        for (Post post1 : client.getPosts()) {
            if (post1.equals(post)) { // if the post I'm trying to save in watchlist is already there
                return false;
            }
        }

        client.getPosts().add(post);
        clientDao.save(client);
        System.out.println(username + " has these items in watchlist:");
        for (Post post1 : client.getPosts()) {
            System.out.println(post1.getTitle());
        }
        return true;
    }

    public boolean checkInWatchlist(String username, long id) {
        Client client = findClientByUsername(username); // username = alexmoraru
        Post post = findPostById(id); // post from all posts table
        System.out.println("All posts from database:");
        for (Post post1 : postDao.findAll()) {
            System.out.println(post1.getTitle());
        }
        System.out.println(username + " has these items in watchlist:");
        for (Post post1 : client.getPosts()) {
            System.out.println(post1.getTitle());
        }

        for (Post post1 : client.getPosts()) {
            if (post.equals(post1)) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteFromWatchlist(String username, long id) {
        if (checkInWatchlist(username, id)) { // this method checks if the item with the certain id is in the watchlist
            Client client = findClientByUsername(username); // find the client
            Post post = findPostById(id); // find the post with this id in the database
            List<Client> allClients = clientDao.findAll(); // allClients has all the clients from the database
            for (Post post1 : client.getPosts()) { // traverse the watchlist
                if (post.equals(post1)) { // if the post from DB is in watchlist
                    postDao.save(post); // save the post from DB again bc when I tried to delete it from watchlist, then was deleted from the DB too
                    for(Client dummyClient : allClients) { // traverse all clients
                        if(dummyClient.getPostList().contains(post1)) { // if the client posted the post from watchlist
                            dummyClient.getPostList().add(post); // add the post again bc otherwise it will be deleted completely
                            dummyClient.setPostList(dummyClient.getPostList()); // reset the list of posted items again
                            clientDao.save(dummyClient); // and save the client again with the new list of items
                        }
                    }
                    client.getPosts().remove(post1); // and now the post can be deleted from watchlist, and the other list of posts will remain intact
                    clientDao.save(client); // again save the client with the new list of items
                    System.out.println("All posts from database:"); // display the items in the database to see that it won't delete the post again
                    for (Post post2 : postDao.findAll()) {
                        System.out.println(post2.getTitle());
                    }
                    System.out.println(username + " has these items in watchlist:"); // also display the watchlist to see the difference between these 2 lists
                    for (Post post2 : client.getPosts()) {
                        System.out.println(post2.getTitle());
                    }
                    return true; // this means that the post was successfully deleted
                }
            }
        }
        return false; // the post was not in the watchlist
    }

    public void deleteFromAllWatchlists(long postId) {
        List<Client> clients = clientDao.findAll();
        Post post = postDao.findPostById(postId);
        for(Client client : clients) {
            if(client.getPosts().contains(post)) {
                System.out.println(client.getUsername() + " has " + post.getTitle() + " in his watchlist");
                deleteFromWatchlist(client.getUsername(), postId);
                System.out.println("And now the post should be deleted from his watchlist");
            }
            System.out.println("This is " + client.getUsername() + " watchlist");
            for(Post post1 : client.getPosts()) {
                System.out.println(post1.getTitle());
            }
        }
    }

    public void deleteMyPost(long clientId, long postId) {
        Client client = clientDao.findClientById(clientId);
        Post postToDelete = postDao.findById(postId).get();
        postDao.delete(postToDelete);
        clientDao.save(client);
        deleteFromAllWatchlists(postId);
        clientDao.save(client);
    }

    @Override
    public String exportClientDetails(Long clientId, String fileType) {
        Client client = clientDao.findClientById(clientId);
        FileExporter fileExporter;
        if (fileType.equals(FileType.XML)) {
            fileExporter = new XMLFileExporter();
            System.out.println(fileExporter.exportData(client));
            return fileExporter.exportData(client);
        } else if (fileType.equals(FileType.TXT)) {
            fileExporter = new TXTFileExporter();
            return fileExporter.exportData(client);
        }
        return null;
    }

    public int onlineusers() {
        List<Client> clients = clientDao.findAll();
        int nbOfClientsOnline = 0;
        for(Client client : clients) {
            if(client.isLoggedIn()) {
                nbOfClientsOnline++;
            }
        }
        return nbOfClientsOnline;
    }

    public List<Post> yourWatchlist(long clientId) {
        Client client = clientDao.findClientById(clientId);
        // System.out.println("In yourWatchlist function");
        /*for(Post post : client.getPosts()) {
            System.out.println(post.getTitle());
        }*/
        return client.getPosts();
    }

    public boolean isMyPost(long clientId, long postId) {
        Client client = clientDao.findClientById(clientId);
        for(Post post : client.getPostList()) {
            if(post.getId() == postId) {
                return true;
            }
        }
        return false;
    }
}
