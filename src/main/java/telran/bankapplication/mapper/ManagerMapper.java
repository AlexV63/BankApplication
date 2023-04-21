package telran.bankapplication.mapper;

import org.mapstruct.Mapper;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.entity.Manager;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDTO toDTO(Manager manager);
    List<ManagerDTO> listToDTO(List<Manager> managers);
 }