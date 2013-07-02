package com.codesolid.tests;

import com.codesolid.goals.model.dto.Account;
import com.codesolid.goals.security.SiteUser;
import com.codesolid.goals.security.SiteUserDetailsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Class com.codesolid.tests.TestSiteUserDetailsService
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="txMgr", defaultRollback=true)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring-config.xml"})
public class TestSiteUserDetailsService {

    @Autowired
    DriverManagerDataSource dataSource;

    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp()
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @After
    public void tearDown(){
    }

    // TODO if user doesn't exist at low level get: org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0
    // What should happen?
    //
    // Test getting a pre-defined user
    @Test
    public void testGetUser() {
        SiteUserDetailsService svc = new SiteUserDetailsService();
        svc.setJdbcTemplate(jdbcTemplate);
        UserDetails userDetails = svc.loadUserByUsername("elitepropertiesbroker@gmail.com");
        assertTrue(userDetails.getClass().getName().contains("SiteUser"));
        assertNotNull(userDetails.getPassword());
        Account account = ((SiteUser) userDetails).getAccount();
        assertNotNull(account);
        assertEquals(account.getDomainName(), "codesolid");
    }

    @Test
    @Rollback
    @Transactional
    public void testStoreAndQuery() {
        SiteUserDetailsService svc = new SiteUserDetailsService();
        svc.setJdbcTemplate(jdbcTemplate);
        Account acct = new Account();
        acct.setDomainName("ChickenTooth");
        SiteUser su = new SiteUser();
        su.setAccount(acct);
        String userName = "joe@ChickenTooth.org";
        su.setUsername(userName);
        su.setPassword("SecretButNotEncrypted");

        su.setEnabled(true);
        svc.createUser(su);

        SiteUser su2 = (SiteUser) svc.loadUserByUsername(userName);

        assertNotNull(su2);
        assertEquals(su.getEmail(), su2.getEmail());
        assertEquals(su.getPassword(), su2.getPassword());
        assertEquals(su.getAccount().getDomainName(), su2.getAccount().getDomainName());
        assertEquals(su.isEnabled(), su2.isEnabled());

    }
}
