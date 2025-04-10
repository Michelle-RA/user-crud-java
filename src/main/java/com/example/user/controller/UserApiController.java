package com.example.user.controller;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserRepository repo;

    public UserApiController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> getAll() { return repo.findAll(); }

    @PostMapping
    public User create(@RequestBody User user) { return repo.save(user); }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User u) {
        User user = repo.findById(id).orElseThrow();
        user.setName(u.getName());
        user.setEmail(u.getEmail());
        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}

