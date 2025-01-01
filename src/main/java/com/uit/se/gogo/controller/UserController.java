package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public DataResponse<User> getUserById(@PathVariable String id) {
        var user = userService.findById(id);
        return new DataResponse<>(user);
    }

    @PatchMapping
    public DataResponse<User> updateUser(@RequestBody User userRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRequest.setId(user.getId());
        var updated = userService.update(userRequest);
        return new DataResponse<>(updated);
    }

    @PutMapping("/{id}/avatar")
    public DataResponse<User> updateUserAvatar(@PathVariable String id,
                                               @RequestPart MultipartFile file) {
        var user = userService.updateAvatar(id, file);
        return new DataResponse<>(user);
    }

    @PutMapping("/{id}/cover")
    public DataResponse<User> updateUserCover(@PathVariable String id,
                                               @RequestPart MultipartFile file) {
        var user = userService.updateCover(id, file);
        return new DataResponse<>(user);
    }
}
