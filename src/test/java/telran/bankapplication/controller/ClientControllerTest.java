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
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.service.ClientService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@DisplayName("Client Controller Test class")
@WithMockUser
class ClientControllerTest {
    @MockBean
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    ClientDTO expectedClientDTO = new ClientDTO("Ali", "Baba", "alibaba@odessa.mail",
            "322223","ACTIVE", "***","xxx");

    @Test
    void getClientEmail() throws Exception {
        Mockito.when(clientService.findClientByEmail("alibaba@odessa.mail")).thenReturn(expectedClientDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/email/alibaba@odessa.mail"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ali"))
                .andExpect(jsonPath("$.lastName").value("Baba"))
                .andExpect(jsonPath("$.email").value("alibaba@odessa.mail"))
                .andExpect(jsonPath("$.taxCode").value("322223"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.createdAt").value("***"))
                .andExpect(jsonPath("$.updatedAt").value("xxx"));
        Mockito.verify(clientService,Mockito.times(1))
                .findClientByEmail("alibaba@odessa.mail");
    }

    @Test
    void getClientName() throws Exception {
        Mockito.when(clientService.findClientByName("Baba")).thenReturn(expectedClientDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients/name/Baba"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ali"))
                .andExpect(jsonPath("$.lastName").value("Baba"))
                .andExpect(jsonPath("$.email").value("alibaba@odessa.mail"))
                .andExpect(jsonPath("$.taxCode").value("322223"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.createdAt").value("***"))
                .andExpect(jsonPath("$.updatedAt").value("xxx"));
        Mockito.verify(clientService,Mockito.times(1))
                .findClientByName("Baba");
    }
}


