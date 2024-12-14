package com.thinne.frontend.controllers;

import com.thinne.backend.models.User;
import com.thinne.backend.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class userController {
    @Autowired
    private UserTableRepository userTableRepository;
    @RequestMapping("")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userTableRepository.findAll());
    }
}
