package com.ltp.furniture_store.service;
import com.ltp.furniture_store.entity.PermissionType;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import com.ltp.furniture_store.repository.PermissionTypeRepository;
import com.ltp.furniture_store.repository.RegisteredCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
    public class RegisteredCustomerService {

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


    public RegisteredCustomer findUserById(Integer id) {
        return registeredCustomerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Optional<RegisteredCustomer> findUserByEmail(String email) {
        return registeredCustomerRepository.findByEmail(email);
    }

    public List<RegisteredCustomer> getAllCustomers() {
        return registeredCustomerRepository.findAll();
    }
}


