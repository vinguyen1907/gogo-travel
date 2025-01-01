package com.uit.se.gogo.service;

import com.uit.se.gogo.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User findById(String id);

    User update(User currentUser, User user);

    User updateAvatar(String id, MultipartFile image);

    User updateCover(String id, MultipartFile image);
}
