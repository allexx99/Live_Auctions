package com.example.Live.Auctions.dao;

import com.example.Live.Auctions.dto.ClientDTO;
import com.example.Live.Auctions.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Long> {
    Client findClientByUsername(String username);
    Client findClientById(long id);
}
