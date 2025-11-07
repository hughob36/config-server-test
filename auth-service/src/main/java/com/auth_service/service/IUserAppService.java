package com.auth_service.service;


import com.auth_service.model.UserApp;

import java.util.List;

public interface IUserAppService {

    public List<UserApp> findAll();
    public UserApp findById(Long id);
    public UserApp save(UserApp userApp);
    public void deleteBYId(Long id);
    public UserApp updateById(Long id, UserApp userApp);
}
