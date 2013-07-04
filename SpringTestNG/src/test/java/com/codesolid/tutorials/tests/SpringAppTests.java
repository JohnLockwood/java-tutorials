package com.codesolid.tutorials.tests;

import com.codesolid.tutorials.HelloService;
import junit.framework.Assert;
import org.testng.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class SpringAppTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello() {
        Assert.assertEquals("Hello world!", helloService.sayHello());
    }
}
