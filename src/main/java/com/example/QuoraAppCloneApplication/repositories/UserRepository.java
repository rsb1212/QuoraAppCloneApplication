package com.example.QuoraAppCloneApplication.repositories;

import com.example.QuoraAppCloneApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
