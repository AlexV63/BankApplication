package telran.bankapplication.mapper;

import org.mapstruct.Mapper;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.entity.Client;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    List<ClientDTO> listToDTO(List<Client> clients);

}
