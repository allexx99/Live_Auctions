package com.example.Live.Auctions.service;

import com.example.Live.Auctions.dto.ClientDTO;
import com.example.Live.Auctions.dto.PostDTO;
import com.example.Live.Auctions.dto.UserDTO;
import com.example.Live.Auctions.model.Client;
import com.example.Live.Auctions.model.Post;
import com.example.Live.Auctions.model.User;

import java.util.List;

public interface ClientServiceInterface {

    Client addClient(ClientDTO clientDTO);
    List<Client> getClients();
    void updateClient(long id, ClientDTO clientDTO);
    void deleteClient(long id);
    ClientDTO login(UserDTO userDTO);
    Client findClientById(long id);
    Client findClientByUsername(String username);
    void saveToMyPostList(String name, PostDTO postDTO);
    Post findPostById(long id);
    boolean saveToMyWatchlist(String username, long id);
    boolean checkInWatchlist(String username, long id);
    boolean deleteFromWatchlist(String username, long id);

    //XML
    String exportClientDetails(Long clientId, String fileType);
}
