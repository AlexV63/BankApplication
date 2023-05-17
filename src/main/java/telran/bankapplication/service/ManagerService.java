package telran.bankapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.exception.ManagerRequestException;
import telran.bankapplication.mapper.ManagerMapper;
import telran.bankapplication.repository.ManagerRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public ManagerDTO findManagerByName(String name){
        return managerMapper.toDTO(managerRepository.findByName(name)
                .orElseThrow(() -> new ManagerRequestException("Manager with name: " + name + " doesn't exist in database")));
            }
    public ManagerDTO findManagerById(UUID id){
        return managerMapper.toDTO(managerRepository.findById(id)
                .orElseThrow(() -> new ManagerRequestException("Manager with id: " + id + " doesn't exist in database")));
    }


}
