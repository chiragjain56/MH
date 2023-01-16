package com.example.masaimailapp.repository;

import com.example.masaimailapp.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPasswordRepository extends JpaRepository<UserPassword, String> {
}
