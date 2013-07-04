package com.codesolid.tutorials.tests;

// Generally needed:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

// Application specific
import com.codesolid.tutorials.UserStory;
import com.codesolid.tutorials.User;

@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class ContextTests extends AbstractTestNGSpringContextTests {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected ApplicationContext ac;

    // Using ApplicationContext
    // The getBean method is the workhorse of the Spring ApplicationContext.
    // The user field in the UserStory object was set up (dependency injected)
    // by Spring:
    @Test
    public void testUserNotNull() {
        UserStory story = (UserStory) ac.getBean("userStory");
        assertNotNull(story.getUser());
    }

    // Not using ApplicationContext
    // Regular non-Spring instantiation
    // Instantiating our own UserStory, you can see that the user
    // is null.
    @Test
    public void testUserStoryNotFromContext() {
        UserStory story = new UserStory();
        assertNull(story.getUser());
    }

    // Not using ApplicationContext
    // Regular non-Spring instantiation
    // The default role if you just call the constructor
    // is "User".
    @Test
    public void testNonSpringUserDefaultRole()
    {
        User u = new User();
        assertEquals(u.getRole(), "User");
    }

    // Using ApplicationContext
    // Since Spring instantiated this according to the property
    // from spring-config.xml, it overrode the default "user"
    // role with "SuperGenius User".  (This presumably
    // is Wile E. Coyote.)
    @Test
    public void testUserRoleIsAsExpected() {
        UserStory story = (UserStory) ac.getBean("userStory");
        assertEquals(story.getUser().getRole(), "SuperGenius User");
    }
}
