package com.project.qrsis.service;

import com.project.qrsis.dal.dto.UserDTO;
import org.springframework.security.core.Authentication;

public interface MeService {

    UserDTO me(Authentication authentication);
}
