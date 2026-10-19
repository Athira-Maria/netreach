package com.netreach.controller;

import com.netreach.model.Admin;
import com.netreach.model.Resident;
import com.netreach.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/residents")
    public Resident addResident(@RequestBody Resident resident) {
        return repo.save(resident);
    }

    @PostMapping("/admins")
    public Admin addAdmin(@RequestBody Admin admin) {
        return repo.save(admin);
    }
}