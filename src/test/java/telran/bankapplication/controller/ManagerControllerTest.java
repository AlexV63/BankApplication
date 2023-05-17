package telran.bankapplication.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.service.ManagerService;


import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManagerController.class)
@DisplayName("Manager controller test class")
@WithMockUser
class ManagerControllerTest {

    @MockBean
    ManagerService managerService;

    @Autowired
    MockMvc mockMvc;

    ManagerDTO expectedManagerDTO = new ManagerDTO("Kurt", "Gurt", "ACTIVE",
            "***", "xxx");

    @Test
    void getManagerByName() throws Exception {
        Mockito.when(managerService.findManagerByName("Gurt")).thenReturn(expectedManagerDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manager/Gurt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Kurt"))
                .andExpect(jsonPath("$.lastName").value("Gurt"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.createdAt").value("***"))
                .andExpect(jsonPath("$.updatedAt").value("xxx"));
        Mockito.verify(managerService,Mockito.times(1))
                .findManagerByName("Gurt");
    }

    UUID uuid = UUID.randomUUID();
    @Test
    void getManagerById() throws Exception {
        Mockito.when(managerService.findManagerById(uuid)).thenReturn(expectedManagerDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/manager/id/" + uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Kurt"))
                .andExpect(jsonPath("$.lastName").value("Gurt"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.createdAt").value("***"))
                .andExpect(jsonPath("$.updatedAt").value("xxx"));
        Mockito.verify(managerService,Mockito.times(1))
                .findManagerById(uuid);
    }
}


