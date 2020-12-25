package com.project.qrsis.controller;

import com.project.qrsis.dal.dto.UserDTO;
import com.project.qrsis.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

    private final MeService meService;

    @Autowired
    public MeController(MeService meService) {
        this.meService = meService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {

        UserDTO me = meService.me(authentication);
        if (me != null)
            return ResponseEntity.ok(me);
        else
            return ResponseEntity.noContent().build();
    }
}
