package com.auth_service.service;

import com.auth_service.dto.AuthRequestDTO;
import com.auth_service.dto.AuthResponseDTO;

public interface IAuthenticationService {

    public AuthResponseDTO loginUser(AuthRequestDTO authRequestDTO);
}
