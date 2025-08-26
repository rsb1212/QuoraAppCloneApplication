package com.example.QuoraAppCloneApplication.repositories;

import com.example.QuoraAppCloneApplication.models.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    Page<Answer> findByAnswerId(Long questionId,Pageable pageable);
}
