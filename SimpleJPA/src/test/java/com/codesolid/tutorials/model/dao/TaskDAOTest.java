package com.codesolid.tutorials.model.dao;

import com.codesolid.tutorials.model.dto.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:spring-context.xml")
@TransactionConfiguration(defaultRollback = false)
public class TaskDAOTest {

    @Autowired
    ApplicationContext appContext = null;

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager entityManager;


    @Test
    @Transactional
    public void canSaveTaskWithContainerManagedTransaction() {
        TaskDAO impl = new TaskDAO(entityManager);

        long records = impl.count();

        Task task = new Task();
        task.setTitle("Learn some Spring JPA");
        task.setDescription("Try saving a test task with a Container managed transaction for example");

        impl.saveAndFlush(task);
        assertNotNull(task.getId());

        assert(impl.count() == records + 1);

    }

}