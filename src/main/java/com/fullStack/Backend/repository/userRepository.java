package com.fullStack.Backend.repository;


import com.fullStack.Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long> {
}
