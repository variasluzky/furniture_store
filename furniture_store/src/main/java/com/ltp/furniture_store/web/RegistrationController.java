package com.ltp.furniture_store.web;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import com.ltp.furniture_store.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisteredCustomer registeredCustomer) {
        System.out.println("Received registration request with data: " + registeredCustomer);
        if (registeredCustomer.getPassword() == null) {
            System.out.println("Password is null for incoming registration request.");
            return ResponseEntity.badRequest().body("Password cannot be null");
        }
        try {
            RegisteredCustomer savedCustomer = registrationService.registerUser(registeredCustomer);
            return ResponseEntity.ok(savedCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing registration: " + e.getMessage());
        }
    }


}

