package com.example.QuoraAppCloneApplication.repositories;

import com.example.QuoraAppCloneApplication.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}
