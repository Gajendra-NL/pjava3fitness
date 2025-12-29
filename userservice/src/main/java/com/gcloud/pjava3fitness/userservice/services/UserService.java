package com.gcloud.pjava3fitness.userservice.services;

import com.gcloud.pjava3fitness.userservice.dto.RegisterRequest;
import com.gcloud.pjava3fitness.userservice.dto.UserResponse;
import com.gcloud.pjava3fitness.userservice.models.User;
import com.gcloud.pjava3fitness.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;

    public UserResponse register(RegisterRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            User existingUser = repository.findByEmail(request.getEmail());

            UserResponse response = new UserResponse();
            response.setId(existingUser.getId());
            response.setPassword(existingUser.getPassword());
            response.setEmail(existingUser.getEmail());
            response.setFirstName(existingUser.getFirstName());
            response.setLastName(existingUser.getLastName());
            response.setCreatedAt(existingUser.getCreatedAt());
            response.setUpdatedAt(existingUser.getUpdatedAt());

            return response;
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setKeycloakId(request.getKeycloakId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setPassword(savedUser.getPassword());
        response.setEmail(savedUser.getEmail());
        response.setKeycloakId(savedUser.getKeycloakId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }

    public UserResponse getUserProfile(String userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

//    public Boolean existsByUserId(String userId) {
//        return repository.existsById(userId);
//    }
    public  Boolean existsByUserId(String userId) {
        return  repository.existsByKeycloakId(userId);
    }
}
