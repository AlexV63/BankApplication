package telran.bankapplication.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.service.ManagerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "The Manager doesn't exist"),
    })
    @GetMapping(path = "{name}")
    public ManagerDTO getManagerName(@PathVariable("name") String name) {
        return managerService.findManagerByName(name);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "The Manager with ID doesn't exist"),
    })
    @GetMapping("/id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO getManagerById(@PathVariable("managerId") UUID managerId) {
        return managerService.findManagerById(managerId);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval"),
            @ApiResponse(responseCode = "400", description = "Managers don't exist"),
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDTO> getAllManagers(){
        return managerService.getAllManagers();
    }

}
