package com.uit.se.gogo.service.impl;

import com.uit.se.gogo.entity.User;
import com.uit.se.gogo.repository.UserRepository;
import com.uit.se.gogo.service.CloudinaryService;
import com.uit.se.gogo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User update(User currentUser, User requestUser) {
        if (requestUser.getFullName() != null && !requestUser.getFullName().equals(currentUser.getFullName())) {
            currentUser.setFullName(requestUser.getFullName());
        }
        if (requestUser.getEmail() != null && !requestUser.getEmail().equals(currentUser.getEmail())) {
            currentUser.setEmail(requestUser.getEmail());
        }
        if (requestUser.getPhoneNumber() != null && !requestUser.getPhoneNumber().equals(currentUser.getPhoneNumber())) {
            currentUser.setPhoneNumber(requestUser.getPhoneNumber());
        }
        if (requestUser.getAddress() != null && !requestUser.getAddress().equals(currentUser.getAddress())) {
            currentUser.setAddress(requestUser.getAddress());
        }
        if (requestUser.getDateOfBirth() != null && !requestUser.getDateOfBirth().equals(currentUser.getDateOfBirth())) {
            currentUser.setDateOfBirth(requestUser.getDateOfBirth());
        }

        return userRepository.save(currentUser);
    }

    @Override
    public User updateAvatar(String id, MultipartFile image) {
        User user = this.findById(id);
        var result = cloudinaryService.uploadFile(image, "gogo/avatars/", id);
        if (result != null) {
            user.setAvatarUrl((String) result.get("url"));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updateCover(String id, MultipartFile image) {
        User user = this.findById(id);
        var result = cloudinaryService.uploadFile(image, "gogo/covers/", id);
        if (result != null) {
            user.setCoverUrl((String) result.get("url"));
            return userRepository.save(user);
        }
        return null;
    }
}
