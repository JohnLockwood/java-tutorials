package com.codesolid.tests;

import com.codesolid.goals.model.dao.AccountDAO;
import com.codesolid.goals.model.dto.Account;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Author: John Lockwood
 * Date: 7/1/13
 * Time: 12:34 PM
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="txMgr", defaultRollback=true)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring-config.xml"})
public class TestAccountDAO {
    @Autowired
    DriverManagerDataSource dataSource;

    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp()
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    @Rollback
    @Transactional
    public void testInsertAndQueryAccount() {
        String testSubdomain = "my_cool_sub";
        AccountDAO dao = new AccountDAO(jdbcTemplate);
        Account toSave = new Account();
        toSave.setDomainName(testSubdomain);
        Account loaded = dao.createAccount(toSave);
        assertNotNull(loaded.getId());
        assertTrue(loaded.getId() != 0);

    }

}
