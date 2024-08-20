/*
package com.example.Live.Auctions;

import com.example.Live.Auctions.dao.ClientDao;
import com.example.Live.Auctions.model.Client;
import com.example.Live.Auctions.service.Impl.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
public class ClientTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    ClientDao clientDao;

    @Test
    public void getClientsTest() {

        //V1
        */
/*when(clientDao.findAll()).thenReturn(Stream
                .of(new Client("alexx", "drew", "alexxdrew", "password", 23, "Software Engineer"), new Client("andrei", "drew", "andreidrew", "password1", 25, "Software Engineer"))
                .collect(Collectors.toList()));
        assertEquals(2, clientService.getClients().size());*//*


        //V2
        List<Client> clientList = new ArrayList<>();

        clientList.add(new Client("Alexx", "Drew", "alexxdrew", "password", 23, "Software Engineer"));
        clientList.add(new Client("Andrei", "Drew", "andreidrew", "password1", 25, "Software Engineer"));

        when(clientDao.findAll()).thenReturn(clientList);

        //List<Client> result = clientDao.findAll();
        List<Client> result = clientService.getClients();

        assertEquals(2, result.size());
        assertEquals("Alexx", result.get(0).getFirstName());
        assertEquals("Drew", result.get(0).getLastName());
        assertEquals("alexxdrew", result.get(0).getUsername());
        assertEquals("password", result.get(0).getPassword());
        assertEquals(23, result.get(0).getAge());
        assertEquals("Software Engineer", result.get(0).getOccupation());

        assertEquals("Andrei", result.get(1).getFirstName());
        assertEquals("Drew", result.get(1).getLastName());
        assertEquals("andreidrew", result.get(1).getUsername());
        assertEquals("password1", result.get(1).getPassword());
        assertEquals(25, result.get(1).getAge());
        assertEquals("Software Engineer", result.get(1).getOccupation());
    }

    @Test
    public void createClientTest() {

        //V1
        */
/*Client client = new Client();
        client.setFirstName("Alexx");
        client.setLastName("Drew");
        client.setUsername("alexxdrew");
        client.setPassword("password");
        client.setAge(23);
        client.setOccupation("Software Engineer");

        when(clientDao.save(client)).thenReturn(client);
        assertEquals(client, clientService.addClient(client));*//*


        //V2
        Client client = new Client();
        client.setFirstName("Alexx");
        client.setLastName("Drew");
        client.setUsername("alexxdrew");
        client.setPassword("password");
        client.setAge(23);
        client.setOccupation("Software Engineer");

        when(clientDao.save(any(Client.class))).thenReturn(new Client());

        //clientDao.save(client);
        Client saveClient = clientService.addClient(client);

        */
/*ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
        verify(clientDao, times(1)).save(captor.capture());
        Client saveClient = captor.getValue();*//*


        assertEquals("Alexx", saveClient.getFirstName());
        assertEquals("Drew", saveClient.getLastName());
        assertEquals("alexxdrew", saveClient.getUsername());
        assertEquals("password", saveClient.getPassword());
        assertEquals(23, saveClient.getAge());
        assertEquals("Software Engineer", saveClient.getOccupation());

    }

    @Test
    public void deleteClientTest() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstName("Alexx");
        client.setLastName("Drew");
        client.setUsername("alexxdrew");
        client.setPassword("password");
        client.setAge(23);
        client.setOccupation("Software Engineer");

        clientService.addClient(client);
        clientService.deleteClient(1L);
        verify(clientDao, times(1)).delete(client);
    }

    @Test
    public void updateClientTest() {
        long id = 0L;
        Client client = new Client();
        client.setFirstName("Alexx");
        client.setLastName("Drew");
        client.setAge(23);
        client.setUsername("alexxdrew");
        client.setPassword("password");
        client.setOccupation("Software Engineer");

        Client existingClient = new Client();
        existingClient.setId(id);
        existingClient.setFirstName("andrei");
        existingClient.setLastName("Drew");
        existingClient.setUsername("andreidrew");
        existingClient.setPassword("password");
        existingClient.setAge(23);
        existingClient.setOccupation("Software Engineer");

        when(clientDao.findById(id)).thenReturn(Optional.of(existingClient));
        when(clientDao.save(existingClient)).thenReturn(existingClient);

        Client updateClient = clientService.updateClient(id, client);
        */
/*updateClient.setId(client.getId());
        updateClient.setFirstName(client.getFirstName());
        updateClient.setLastName(client.getLastName());
        updateClient.setUsername(client.getUsername());
        updateClient.setPassword(client.getPassword());
        updateClient.setAge(client.getAge());
        updateClient.setOccupation(client.getOccupation());*//*


        assertEquals(client.getId(), updateClient.getId());
        assertEquals(client.getFirstName(), updateClient.getFirstName());
        assertEquals(client.getLastName(), updateClient.getLastName());
        assertEquals(client.getUsername(), updateClient.getUsername());
        assertEquals(client.getPassword(), updateClient.getPassword());
        assertEquals(client.getAge(), updateClient.getAge());
        assertEquals(client.getOccupation(), updateClient.getOccupation());

    }
}
*/
