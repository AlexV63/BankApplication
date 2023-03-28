package telran.bankapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
