package com.example.questionbonus_userprofile_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.questionbonus_userprofile_api.lib.ApiResponse;
import com.example.questionbonus_userprofile_api.model.UserProfile;

@RestController
@RequestMapping("/api/users")
public class UserPofileAPI {
    List<UserProfile> users = new ArrayList<>();

    public UserPofileAPI(){
        users.add(new UserProfile(
            1,
            "john_doe",
            "john@example.com",
            "John Doe",
            28,
            "RW",
            "Backend developer",
            true
        ));

        users.add(new UserProfile(
            2,
            "jane_smith",
            "jane.smith@example.com",
            "Jane Smith",
            24,
            "KE",
            "UI/UX designer",
            true
        ));

        users.add(new UserProfile(
            3,
            "peter_k",
            "peter.k@example.com",
            "Peter Kamau",
            35,
            "KE",
            "Project manager",
            false
        ));

        users.add(new UserProfile(
            4,
            "alice_m",
            "alice.m@example.com",
            "Alice Mugenzi",
            22,
            "RW",
            "Computer science student",
            true
        ));

        users.add(new UserProfile(
            5,
            "samuel_t",
            "samuel.t@example.com",
            "Samuel Taye",
            31,
            "ET",
            "DevOps engineer",
            true
        ));

        users.add(new UserProfile(
            6,
            "fatima_a",
            "fatima.a@example.com",
            "Fatima Ahmed",
            27,
            "EG",
            "Data analyst",
            false
        ));

        users.add(new UserProfile(
            7,
            "david_n",
            "david.n@example.com",
            "David Njoroge",
            40,
            "KE",
            "Technical lead",
            true
        ));

        users.add(new UserProfile(
            8,
            "marie_l",
            "marie.l@example.com",
            "Marie Laurent",
            29,
            "FR",
            "Product owner",
            true
        ));

        users.add(new UserProfile(
            9,
            "eric_b",
            "eric.b@example.com",
            "Eric Brown",
            34,
            "US",
            "Cloud architect",
            false
        ));

        users.add(new UserProfile(
            10,
            "amina_h",
            "amina.h@example.com",
            "Amina Hassan",
            21,
            "SO",
            "Information systems student",
            true
        ));

    }

    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUsers() {
        return new ApiResponse<>(true, "Users retrieved successfully", users);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserProfile> getUserById(@PathVariable long id) {
        UserProfile user = users.stream()
                .filter(u -> u.getUserId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with ID " + id + " not found"
                ));
        return new ApiResponse<>(true, "User retrieved successfully", user);
    }

    @GetMapping("/search")
    public ApiResponse<List<UserProfile>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge
    ) {
        if (minAge != null && minAge < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "minAge cannot be negative");
        }
        if (maxAge != null && maxAge < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maxAge cannot be negative");
        }
        if (minAge != null && maxAge != null && maxAge < minAge) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "maxAge must be >= minAge");
        }

        String u = username == null ? null : username.trim().toLowerCase(Locale.ROOT);
        String c = country == null ? null : country.trim().toLowerCase(Locale.ROOT);

        List<UserProfile> result = users.stream()
                .filter(x -> u == null || (x.getUsername() != null
                        && x.getUsername().toLowerCase(Locale.ROOT).contains(u)))
                .filter(x -> c == null || (x.getCountry() != null
                        && x.getCountry().toLowerCase(Locale.ROOT).equals(c)))
                .filter(x -> minAge == null || x.getAge() >= minAge)
                .filter(x -> maxAge == null || x.getAge() <= maxAge)
                .collect(Collectors.toList());

        return new ApiResponse<>(true, "Search results retrieved successfully", result);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserProfile> createUser(@RequestBody UserProfile body) {
        if (body.getUsername() == null || body.getUsername().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }
        if (body.getEmail() == null || body.getEmail().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        if (body.getAge() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Age cannot be negative");
        }

        UserProfile created = new UserProfile(
                body.getUserId(),
                body.getUsername().trim(),
                body.getEmail().trim(),
                body.getFullName(),
                body.getAge(),
                body.getCountry(),
                body.getBio(),
                body.isActive()
        );

        users.add(created);
        return new ApiResponse<>(true, "User profile created successfully", created);
    }

    @PutMapping("/{id}")
    public ApiResponse<UserProfile> updateUser(@PathVariable long id, @RequestBody UserProfile body) {
        UserProfile existing = users.stream()
                .filter(u -> u.getUserId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with ID " + id + " not found"
                ));

        if (body.getUsername() == null || body.getUsername().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required");
        }
        if (body.getEmail() == null || body.getEmail().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        if (body.getAge() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Age cannot be negative");
        }

        existing.setUsername(body.getUsername().trim());
        existing.setEmail(body.getEmail().trim());
        existing.setFullName(body.getFullName());
        existing.setAge(body.getAge());
        existing.setCountry(body.getCountry());
        existing.setBio(body.getBio());
        existing.setActive(body.isActive());

        return new ApiResponse<>(true, "User profile updated successfully", existing);
    }

    @PatchMapping("/{id}/change-status")
    public ApiResponse<UserProfile> activateUser(@PathVariable long id) {
        UserProfile user = users.stream()
                .filter(u -> u.getUserId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User with ID " + id + " not found"
                ));
        user.setActive(!user.isActive());
        return new ApiResponse<>(true, "User profile activated successfully", user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable long id) {
        UserProfile existing = users.stream()
                .filter(u -> u.getUserId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User with ID " + id + " not found"
                ));
        users.remove(existing);
        return new ApiResponse<>(true, "User profile deleted successfully", null);
    }
}
