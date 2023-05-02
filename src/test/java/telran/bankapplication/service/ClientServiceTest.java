package telran.bankapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import telran.bankapplication.entity.Client;
import telran.bankapplication.mapper.ClientMapper;
import telran.bankapplication.registration.token.ConfirmationTokenRepository;
import telran.bankapplication.registration.token.ConfirmationTokenService;
import telran.bankapplication.repository.ClientRepository;

import java.util.Optional;

class ClientServiceTest {

    ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
    ConfirmationTokenRepository confirmationTokenRepository= Mockito.mock(ConfirmationTokenRepository.class);
    ClientService clientService = new ClientService(clientRepository,
            Mockito.mock(ClientMapper.class),
            new BCryptPasswordEncoder(),
            new ConfirmationTokenService(confirmationTokenRepository)) ;

    @Test
    void findClientByEmail() {
        Mockito.when(clientRepository.findByEmail("")).thenReturn(Optional.of(new Client()));
        clientService.findClientByEmail("");
        Mockito.verify(clientRepository, Mockito.times(1))
                .findByEmail("");
    }

    @Test
    void findClientByName() {
        Mockito.when(clientRepository.findByName("")).thenReturn(Optional.of(new Client()));
        clientService.findClientByName("");
        Mockito.verify(clientRepository, Mockito.times(1))
                .findByName("");
    }

    @Test
    void testExceptionDoesClientNotExist() {
        IllegalStateException illegalStateException = Assertions.assertThrows(
                IllegalStateException.class, ()-> {clientService.findClientByName("Mike");});
        Assertions.assertEquals("Client with name: Mike doesn't exist in database", illegalStateException.getMessage());
    }
}