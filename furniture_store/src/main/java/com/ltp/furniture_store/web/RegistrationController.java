package com.ltp.furniture_store.web;
import com.ltp.furniture_store.entity.PermissionType;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import com.ltp.furniture_store.entity.RegistrationDTO;
import com.ltp.furniture_store.repository.PermissionTypeRepository;
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

    @Autowired
    private PermissionTypeRepository permissionTypeRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        // You can now manually create a RegisteredCustomer object if needed
        PermissionType defaultPermission = permissionTypeRepository.findByPermissionStatus("user"); // Assume default permission
        RegisteredCustomer customer = new RegisteredCustomer(
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getPhone(),
                registrationDTO.getEmail(),
                registrationDTO.getPassword(),
                defaultPermission
        );
        customer = registrationService.registerUser(customer);
        return ResponseEntity.ok(customer);
    }


    // GET endpoint for fetching a user by ID
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            RegisteredCustomer customer = registrationService.findUserById(id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        }
    }
}

