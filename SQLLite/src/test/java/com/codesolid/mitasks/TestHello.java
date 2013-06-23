package com.codesolid.mitasks;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestHello {

    @Test
    public void testPasses() {
        String expected = "Hello, JUnit!";
        String hello = "Hello, JUnit!";
        assertEquals(hello, expected);
    }
    @Test
    public void testSQLLite() throws SQLiteException {
        SQLiteConnection db = new SQLiteConnection(new File("testdb.db"));
        db.open(true);
        SQLiteStatement st = db.prepare("CREATE TABLE if NOT EXISTS TEST1(col1 TEXT)");
        try {
            st.stepThrough();

        } finally {
            st.dispose();
        }

        db.dispose();
    }


    @Test
    public void testArray() {

    }
}
