package com.gcloud.pjava3fitness.userservice.repository;

import com.gcloud.pjava3fitness.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Boolean existsByEmail(String email);
}
