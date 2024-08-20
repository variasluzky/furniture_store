package com.ltp.furniture_store.service;
import com.ltp.furniture_store.entity.PermissionType;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import com.ltp.furniture_store.entity.RegistrationDTO;
import com.ltp.furniture_store.repository.PermissionTypeRepository;
import com.ltp.furniture_store.repository.RegisteredCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
    public class RegistrationService {

        @Autowired
        private RegisteredCustomerRepository registeredCustomerRepository;

        @Autowired
        private PermissionTypeRepository permissionTypeRepository;

        public RegisteredCustomer registerUser(RegisteredCustomer registeredCustomer) {
            PermissionType userPermission = permissionTypeRepository.findByPermissionStatus("user");
            registeredCustomer.setPermissions(userPermission);
            registeredCustomer.setCreatedAt(new Date());
            registeredCustomer.setUpdatedAt(new Date());
            return registeredCustomerRepository.save(registeredCustomer);
        }
    public RegisteredCustomer convertToEntity(RegistrationDTO registrationDTO) {
        RegisteredCustomer registeredCustomer = new RegisteredCustomer();
        registeredCustomer.setFirstName(registrationDTO.getFirstName());
        registeredCustomer.setLastName(registrationDTO.getLastName());
        registeredCustomer.setEmail(registrationDTO.getEmail());
        registeredCustomer.setPhone(registrationDTO.getPhone());
        registeredCustomer.setPassword(registrationDTO.getPassword());
        return registeredCustomer;
    }

    public RegisteredCustomer registerUser(RegistrationDTO registrationDTO) {
        RegisteredCustomer registeredCustomer = convertToEntity(registrationDTO);
        return registeredCustomerRepository.save(registeredCustomer);
    }



    public RegisteredCustomer findUserById(Integer id) {
        return registeredCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}


