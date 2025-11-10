package com.auth_service.service;

import com.auth_service.exception.ResourceNotFoundException;
import com.auth_service.model.Role;
import com.auth_service.model.UserApp;
import com.auth_service.repository.IRoleRepository;
import com.auth_service.repository.IUserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAppService implements IUserAppService{

    private final IUserAppRepository userAppRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserApp> findAll() {
        return userAppRepository.findAll();
    }

    @Override
    public UserApp findById(Long id) {
        return userAppRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    public UserApp save(UserApp userApp) {
       UserApp userValid = this.validateAndLoadRole(userApp);
        userValid.setPassword(passwordEncoder.encode(userApp.getPassword()));
        return userAppRepository.save(userValid);
    }

    @Override
    public void deleteBYId(Long id) {
        if(!userAppRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found.");
        }
        userAppRepository.deleteById(id);
    }

    @Override
    public UserApp updateById(Long id, UserApp userApp) {

        UserApp findUser = this.findById(id);
        findUser.setName(userApp.getName());
        findUser.setUsername(userApp.getUsername());
        findUser.setPassword(passwordEncoder.encode(userApp.getPassword()));
        findUser.setEnable(userApp.isEnable());
        findUser.setAccountNotExpired(userApp.isAccountNotExpired());
        findUser.setAccountNotLocked(userApp.isAccountNotLocked());
        findUser.setCredentialNotExpired(userApp.isCredentialNotExpired());
        UserApp userValid = this.validateAndLoadRole(findUser);
        return userAppRepository.save(userValid);
    }

    public UserApp validateAndLoadRole(UserApp userApp) {
        Set<Role> roleSet = new HashSet<>();
        for(Role role : userApp.getRoleSet()) {
            roleRepository.findById(role.getId()).ifPresent(roleSet::add);
        }
        userApp.setRoleSet(roleSet);
        return userApp;
    }

}
