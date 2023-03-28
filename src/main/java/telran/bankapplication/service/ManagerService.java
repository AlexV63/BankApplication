package telran.bankapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.mapper.ManagerMapper;
import telran.bankapplication.repository.ManagerRepository;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public ManagerDTO findManagerByName(String name){
        return managerMapper.toDTO(managerRepository.findByName(name)
                .orElseThrow(() -> new IllegalStateException("Manager with name: " + name + " doesn't exist in database")));
    }

}
