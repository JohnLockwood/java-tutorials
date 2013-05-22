package com.codesolid.tests;

import com.codesolid.tutorials.model.dal.HibernateUtil;
import org.apache.log4j.Level;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.apache.log4j.Logger;

@RunWith( Suite.class )
@Suite.SuiteClasses( {
        TestStorage.class
        /* , Add more test classes here separated by commas*/
} )
public class RuleSuite{

    private static HibernateUtil util;

    // This is a static field.  Per the ClassRule documentation,
    // to use a ClassRule we need a field that is "public, static,
    // and a subtype of of TestRule."
    // See http://junit.czweb.org/apidocs/org/junit/ClassRule.html
    @ClassRule
    public static ExternalResource testRule = new ExternalResource(){
        @Override
        protected void before() throws Throwable{
            Logger.getLogger("com.codesolid.tests").log(Level.DEBUG, "Inside RuleSuite::ExternalResource::before.");
            util = new HibernateUtil();
        }

        @Override
        protected void after(){
            // Nothing to do here in this case.
            Logger.getLogger("com.codesolid.tests").log(Level.DEBUG, "Inside RuleSuite::ExternalResource::after.");
        }
    };
}