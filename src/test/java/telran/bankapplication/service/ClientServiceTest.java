package telran.bankapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import telran.bankapplication.entity.Client;
import telran.bankapplication.entity.Manager;
import telran.bankapplication.mapper.ClientMapper;
import telran.bankapplication.mapper.ManagerMapper;
import telran.bankapplication.repository.ClientRepository;
import telran.bankapplication.repository.ManagerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
    ClientService clientService = new ClientService(clientRepository,
            Mockito.mock(ClientMapper.class));

    @Test
    void findClientByName() {
        Mockito.when(clientRepository.findByName("")).thenReturn(Optional.of(new Client()));
        clientService.findClientByName("");
        Mockito.verify(clientRepository, Mockito.times(1))
                .findByName("");
    }

    @Test
    void testExceptionDoesManagerNotExist() {
        IllegalStateException illegalStateException = Assertions.assertThrows(
                IllegalStateException.class, ()-> {clientService.findClientByName("Mike");});
        Assertions.assertEquals("Client with name: Mike doesn't exist in database", illegalStateException.getMessage());
    }
}