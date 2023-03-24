package telran.bankapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.mapper.ClientMapper;
import telran.bankapplication.mapper.ManagerMapper;
import telran.bankapplication.repository.ClientRepository;
import telran.bankapplication.repository.ManagerRepository;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDTO findClientByLastName(String lastname){
        return clientMapper.toDTO(clientRepository.findByLastName(lastname)
                .orElseThrow(() -> new IllegalStateException("Client with name: " + lastname + " doesn't exists in database")));
    }

}
