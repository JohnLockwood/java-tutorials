package com.codesolid.properties.tests;

import static org.junit.Assert.*;

import com.codesolid.properties.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class ContextTests {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ApplicationContext ac;

    /* Verify that we've correctly injected a property
    from the spring directory (in this case, the
    common.properties file).  Property files in this
    directory define properties that can be used in
    all environments. */
    @Test
    public void testApplicationName() {
        String expected = "The Greatest Sample Ever";
        TestBean test = (TestBean) ac.getBean("testBean");
        String actual = test.getApplicationName();
        assertEquals(actual, expected);
    }

    /* Verify that we've correctly injected a property
    from the test directory's db.properties.
    Property files in the test directory only apply
    to the test environment and can be overridden in  the
    production directory (for example). */

    @Test
    public void testDatabaseConfiguredCorrectly() {
        String expected = "myuser";
        TestBean test = (TestBean) ac.getBean("testBean");
        String actual = test.getDatabaseUser();
        assertEquals(actual, expected);
    }
}
