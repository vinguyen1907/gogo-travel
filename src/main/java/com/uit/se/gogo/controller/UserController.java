package com.uit.se.gogo.controller;

import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.response.DataResponse;
import com.uit.se.gogo.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PatchMapping("/{id}")
    public DataResponse<User> updateUser(@PathVariable String id, @RequestBody User userRequest) {
        userRequest.setId(id);
        var user = userService.update(userRequest);
        return new DataResponse<>(user);
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
