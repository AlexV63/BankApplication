package telran.bankapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import telran.bankapplication.entity.Manager;
import telran.bankapplication.exception.ManagerNotFoundException;
import telran.bankapplication.exception.ManagerRequestException;
import telran.bankapplication.mapper.ManagerMapper;
import telran.bankapplication.repository.ManagerRepository;

import java.util.Optional;

class ManagerServiceTest {

    ManagerRepository managerRepository = Mockito.mock(ManagerRepository.class);
    ManagerService managerService = new ManagerService(managerRepository, Mockito.mock(ManagerMapper.class));

    @Test
    void findManagerByName() {
        Mockito.when(managerRepository.findByName("")).thenReturn(Optional.of(new Manager()));
        managerService.findManagerByName("");
        Mockito.verify(managerRepository, Mockito.times(1))
                .findByName("");
    }

        @Test
    void testExceptionDoesManagerNotExist() {
        ManagerRequestException managerRequestException = Assertions.assertThrows(
                ManagerRequestException.class, ()-> {managerService.findManagerByName("John");});
        Assertions.assertEquals("Manager with name: John doesn't exist in database", managerRequestException.getMessage());
    }

    @Test
    void findManagerById() {
        Mockito.when(managerRepository.findById("")).thenReturn(Optional.of(new Manager()));
        managerService.findManagerById("");
        Mockito.verify(managerRepository, Mockito.times(1))
                .findById("");
    }

    @Test
    void testExceptionDoesManagerWithIdNotExist() {
        ManagerRequestException managerRequestException = Assertions.assertThrows(
                ManagerRequestException.class, ()-> {managerService.findManagerById("777333");});
        Assertions.assertEquals("Manager with id: 777333 doesn't exist in database", managerRequestException.getMessage());
    }
}