package telran.bankapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.bankapplication.dto.ClientDTO;
import telran.bankapplication.service.ClientService;


@RestController
    @RequestMapping(path="api/v1/clients")
    @RequiredArgsConstructor
    public class ClientController {
        private final ClientService clientService;

        @GetMapping(path = "{name}")
        public ClientDTO getClientLastName(@PathVariable("name") String name){
            return clientService.findClientByLastName(name);
        }
    }
