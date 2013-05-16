package com.codesolid.tutorials.tests;
import com.codesolid.tutorials.UserStory;
import org.junit.Test;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import static org.junit.Assert.*;

/* Here we run one of the same tests we run in ContextTests, but we separate it out here to demonstrate
 *  we can do it with a POJU (Plain Old JUnit :) test runner.
 */
public class JUnitContextTests {

    ApplicationContext ac;

    @Before
    public void setUp()
    {
        ac = new FileSystemXmlApplicationContext("file:src/main/resources/spring-config.xml");
    }

    @Test
    public void testUserCorrectFromPlainOldJUnitTest() {

        UserStory story = (UserStory) ac.getBean("userStory");
        // Spring is working fine using this app context
        assertEquals(story.getUser().getRole(), "SuperGenius User");

        // In this case our User is not wired up
        UserStory story2 = new UserStory();
        assertNull(story2.getUser());
    }

    @Test
    public void testPrewiredUserCorrect() {
        UserStory story = (UserStory) ac.getBean("userStory");
        assertEquals(story.getUser().getRole(), "SuperGenius User");
    }
}
