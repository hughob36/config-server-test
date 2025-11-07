package com.auth_service.service;

import com.auth_service.exception.ResourceNotFoundException;
import com.auth_service.model.Permission;
import com.auth_service.repository.IPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService implements IPermissionService{

    private final IPermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found."));
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {

        if(!permissionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not found");
        }
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission updateById(Long id, Permission permission) {

        Permission foundPermission = this.findById(id);
        foundPermission.setPermissionName(permission.getPermissionName());
        return permissionRepository.save(foundPermission);
    }
}
