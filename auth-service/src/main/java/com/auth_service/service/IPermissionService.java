package com.auth_service.service;

import com.auth_service.model.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll();
    public Permission findById(Long id);
    public Permission save(Permission permission);
    public void deleteById(Long id);
    public Permission updateById(Long id, Permission permission);

}
