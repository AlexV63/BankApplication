package telran.bankapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import telran.bankapplication.dto.ManagerDTO;
import telran.bankapplication.service.ManagerService;

@RestController
@RequestMapping(path="api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping(path = "{name}")
    public ManagerDTO getManagerName(@PathVariable("name") String name){
        return managerService.findManagerByName(name);
    }

    @GetMapping("/id/{managerId}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDTO getManagerById(@PathVariable("managerId") String managerId) {
        return managerService.findManagerById(managerId);
    }
}
