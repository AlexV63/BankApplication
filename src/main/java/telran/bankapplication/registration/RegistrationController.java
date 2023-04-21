package telran.bankapplication.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.bankapplication.entity.Client;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class  RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(
            @RequestBody Client request, @RequestParam UUID manager_id) {
        System.out.println("\n\n\n" + request);
        System.out.println(manager_id);
        return registrationService.register(request, manager_id);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
