package com.ltp.furniture_store;

import com.ltp.furniture_store.entity.PermissionType;
import com.ltp.furniture_store.entity.Promotion;
import com.ltp.furniture_store.entity.RegisteredCustomer;
import com.ltp.furniture_store.repository.PermissionTypeRepository;
import com.ltp.furniture_store.repository.PromotionRepository;
import com.ltp.furniture_store.repository.RegisteredCustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.math.BigDecimal;
import java.util.Date;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(PermissionTypeRepository permissionTypeRepository, RegisteredCustomerRepository registeredCustomerRepository, PromotionRepository promotionRepository) {
        return args -> {
            // Check if the table is empty
            if (permissionTypeRepository.count() == 0) {
                PermissionType admin = new PermissionType();
                admin.setPermissionStatus("admin");
                permissionTypeRepository.save(admin);


                PermissionType user = new PermissionType();
                user.setPermissionStatus("user");
                permissionTypeRepository.save(user);
            }
            // Check if the RegisteredCustomer table is empty
            if (registeredCustomerRepository.count() == 0) {
                // Create the first admin user
                RegisteredCustomer adminUser = new RegisteredCustomer();
                adminUser.setFirstName("AdminFirstName");
                adminUser.setLastName("AdminLastName");
                adminUser.setPhone("1234567890");
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword("Zxcvbn123!");
                adminUser.setPermissions(permissionTypeRepository.findByPermissionStatus("admin"));
                adminUser.setCreatedAt(new Date());
                adminUser.setUpdatedAt(new Date());
                registeredCustomerRepository.save(adminUser);
            }
            //check if promotions table is empty
            if (promotionRepository.count()== 0){
                //Create first promotion
                Promotion noPromotion= new Promotion();
                noPromotion.setDescription("No Promotion");
                noPromotion.setDiscount(BigDecimal.ZERO);
                promotionRepository.save(noPromotion);
            }
        };
    }
}


