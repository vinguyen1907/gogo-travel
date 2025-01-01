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
    public User update(User user) {
        User currentUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (user.getFullName() != null && !user.getFullName().equals(currentUser.getFullName())) {
            currentUser.setFullName(user.getFullName());
        }
        if (user.getEmail() != null && !user.getEmail().equals(currentUser.getEmail())) {
            currentUser.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber() != null && !user.getPhoneNumber().equals(currentUser.getPhoneNumber())) {
            currentUser.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getAddress() != null && !user.getAddress().equals(currentUser.getAddress())) {
            currentUser.setAddress(user.getAddress());
        }
        if (user.getDateOfBirth() != null && !user.getDateOfBirth().equals(currentUser.getDateOfBirth())) {
            currentUser.setDateOfBirth(user.getDateOfBirth());
        }

        return userRepository.save(user);
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
