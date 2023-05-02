package telran.bankapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.service.ClientService;

@RestController
@RequestMapping(path = "api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping(path = "email/{email}")
    public ClientDTO getClientEmail(@PathVariable("email") String email) {
        return clientService.findClientByEmail(email);
    }

    @GetMapping(path = "name/{name}")
    public ClientDTO getClientName(@PathVariable("name") String name) {
        return clientService.findClientByName(name);
    }

}
