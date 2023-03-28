package telran.bankapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;
import telran.bankapplication.entity.Manager;
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
        IllegalStateException illegalStateException = Assertions.assertThrows(
                IllegalStateException.class, ()-> {managerService.findManagerByName("John");});
        Assertions.assertEquals("Manager with name: John doesn't exist in database", illegalStateException.getMessage());
    }
}