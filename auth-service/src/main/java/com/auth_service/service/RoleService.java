package com.auth_service.service;

import com.auth_service.exception.ResourceNotFoundException;
import com.auth_service.model.Permission;
import com.auth_service.model.Role;
import com.auth_service.repository.IPermissionRepository;
import com.auth_service.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @Override
    public Role save(Role role) {
        Role roleValid = validateAndLoadPermissions(role);
        return roleRepository.save(roleValid);
    }

    @Override
    public void deleteById(Long id) {
        if(!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not exists.");
        }
        roleRepository.deleteById(id);
    }

    @Override
    public Role updateById(Long id, Role role) {
        this.findById(id);
        Role roleValid = this.validateAndLoadPermissions(role);
        return roleValid;
    }

    public Role validateAndLoadPermissions(Role role) {
        Set<Permission> permissionSet = new HashSet<>();
        for(Permission permission : role.getPermissionSet()) {
            permissionRepository.findById(permission.getId()).ifPresent(permissionSet::add);
        }
        role.setPermissionSet(permissionSet);
        return role;
    }
}
