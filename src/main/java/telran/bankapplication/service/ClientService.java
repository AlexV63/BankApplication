package telran.bankapplication.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.entity.Client;
import telran.bankapplication.entity.enums.ClientStatus;
import telran.bankapplication.mapper.ClientMapper;
import telran.bankapplication.entity.ConfirmationToken;
import telran.bankapplication.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private static final String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public ClientDTO findClientByEmail(String email) {
        return clientMapper.toDTO(clientRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Client with email: " + email + " doesn't exist in database")));
    }

    public ClientDTO findClientByName(String name) {
        return clientMapper.toDTO(clientRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("Client with name: " + name + " doesn't exist in database")));
    }

    public List<ClientDTO> getClients() {
        return clientMapper.listToDTO(clientRepository.findAll());
    }


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Transactional
    public String signUpClient(Client client) {
        Optional<Client> newClient = clientRepository.findByEmail(client.getEmail());
        if (newClient.isPresent()) {
            if (newClient.get().getStatus().equals(ClientStatus.ACTIVE)) {
                throw new IllegalStateException("Client with this email already registered");
            } else {
                confirmationTokenService.deleteConfirmationToken(client);
                ConfirmationToken confirmationToken = new ConfirmationToken(client);
                confirmationTokenService.saveConfirmationToken(confirmationToken);
                return confirmationToken.getToken();
            }
        }
        String encodePassword = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(encodePassword);
        clientRepository.save(client);

        ConfirmationToken confirmationToken = new ConfirmationToken(client);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return confirmationToken.getToken();
    }

    @Transactional
    public void activeClient(String email) {
        Client client = clientRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Tht client not found"));
        client.setStatus(ClientStatus.ACTIVE);
        clientRepository.save(client);
    }

    public void enableClient(String email) {
        clientRepository.enableClient(email);
    }


}
