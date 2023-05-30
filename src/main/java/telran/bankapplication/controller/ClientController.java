package telran.bankapplication.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "Client with email doesn't exist"),
    })

    @GetMapping(path = "email/{email}")
    public ClientDTO getClientEmail(@PathVariable("email") String email) {
        return clientService.findClientByEmail(email);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "The Client with name doesn't exist"),
    })

    @GetMapping(path = "name/{name}")
    public ClientDTO getClientName(@PathVariable("name") String name) {
        return clientService.findClientByName(name);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "The Client with name doesn't exist"),
    })

    @GetMapping(path = "all")
    public List<ClientDTO> getClients() {
        return clientService.getClients();
    }

}
