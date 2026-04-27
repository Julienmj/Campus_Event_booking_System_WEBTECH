package mj.Campus_event_system.controller;

import mj.Campus_event_system.model.Registration;
import mj.Campus_event_system.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Registration> register(@RequestBody Registration registration) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.register(registration));
    }

    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.getAllRegistrations());
    }
}
