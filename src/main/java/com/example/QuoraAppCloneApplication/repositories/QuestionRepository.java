package com.example.QuoraAppCloneApplication.repositories;

import com.example.QuoraAppCloneApplication.models.Question;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query("SELECT Q FORM Question q JOIN q.tags t WHERE t.id IN:tagIds")
    Page<Question> findQuestionByTag (Set<Long> tagIds, Pageable pageable);
}
