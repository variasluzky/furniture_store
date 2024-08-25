package com.ltp.furniture_store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ltp.furniture_store.entity.PermissionType;
import com.ltp.furniture_store.repository.PermissionTypeRepository;

import java.util.List;

@Service
public class PermissionTypeService {

    @Autowired
    private PermissionTypeRepository repository;

    // Fetch all permission types
    public List<PermissionType> getAllPermissionTypes() {
        return repository.findAll();
    }

    // Fetch a single permission type by ID
    public PermissionType getPermissionTypeById(Short id) {
        return repository.findById(id).orElse(null);
    }

    // Create a new permission type
    public PermissionType createPermissionType(PermissionType permissionType) {
        return repository.save(permissionType);
    }

    // Update an existing permission type
    public PermissionType updatePermissionType(Short id, PermissionType updatedPermissionType) {
        PermissionType existingPermissionType = repository.findById(id).orElse(null);
        if (existingPermissionType != null) {
           existingPermissionType.setPermissionStatus(updatedPermissionType.getPermissionStatus());
            return repository.save(existingPermissionType);
        }
        return null;
    }

    // Delete a permission type by ID
    public void deletePermissionType(Short id) {
        repository.deleteById(id);
    }
}
