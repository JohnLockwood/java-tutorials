package com.codesolid.tutorials.tests;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class SimpleTest extends AbstractTestNGSpringContextTests {

    @Test
    public void SomeTest() {
        assertTrue(true);
    }
}
