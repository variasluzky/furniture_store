package com.ltp.furniture_store.web;
import com.ltp.furniture_store.IncorrectPasswordException;
import com.ltp.furniture_store.UserNotFoundException;
import com.ltp.furniture_store.entity.PermissionType;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import com.ltp.furniture_store.entity.RegistrationDTO;
import com.ltp.furniture_store.entity.UserDTO;
import com.ltp.furniture_store.repository.PermissionTypeRepository;
import com.ltp.furniture_store.service.RegisteredCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisteredCustomerController {

    @Autowired
    private RegisteredCustomerService registrationService;

    @Autowired
    private PermissionTypeRepository permissionTypeRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {

        PermissionType defaultPermission = permissionTypeRepository.findByPermissionStatus("user");
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
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        try {
            RegisteredCustomer customer = registrationService.findUserById(id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        }
    }

    @PostMapping("/logIn")
    public ResponseEntity<?> logInUser(@RequestParam String email, @RequestParam String password) {
        try {
            RegisteredCustomer customer = registrationService.findUserByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

            if (!customer.getPassword().equals(password)) {
                throw new IncorrectPasswordException("Password is incorrect for email: " + email);
            }
            UserDTO userDTO = new UserDTO(
                    customer.getCustomerId(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getPermissions().getPermissionStatus()
            );

            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
