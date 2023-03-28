package telran.bankapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.mapper.ClientMapper;
import telran.bankapplication.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDTO findClientByName(String lastname){
        return clientMapper.toDTO(clientRepository.findByName(lastname)
                .orElseThrow(() -> new IllegalStateException("Client with name: " + lastname + " doesn't exist in database")));
    }

}
