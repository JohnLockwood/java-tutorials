package com.codesolid.tutorials.model.dao;

import com.codesolid.tutorials.model.dto.Task;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * Class TaskDAO
 * Description:
 */
public class TaskDAO extends SimpleJpaRepository<Task, Long> {

    public TaskDAO(EntityManager entityManager) {
        super(Task.class, entityManager);
    }
}

