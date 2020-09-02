package com.company;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    ServerString a = null;
    ServerString b = null;
    ServerString c = null;
    ServerString a1 = null;
    ServerString b1 = null;
    ServerString c1 = null;
    ServerString invalid = null;
    Main main = null;

    @BeforeEach
    void setUp() {
        main = new Main();
        a = new ServerString("1.1.1");
        b = new ServerString("1");
        c = new ServerString("1.1");
        a1 = new ServerString("1.1.1-alpha");
        b1 = new ServerString("1-alpha");
        c1 = new ServerString("1.1-alpha");
        invalid = new ServerString("1.1.1.1");

    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void format3() {

        ArrayList<String> test = new ArrayList<>();
        test = main.format(a.getVersion());
        assertNotNull(test);
        assertEquals(3,test.size());
        assertEquals("1", test.get(0));
        assertEquals("1", test.get(1));
        assertEquals("1", test.get(2));

    }
    @Test
    void format2() {

        ArrayList<String> test = new ArrayList<>();
        test = main.format(c.getVersion());
        assertNotNull(test);
        assertEquals(3,test.size());
        assertEquals("1", test.get(0));
        assertEquals("1", test.get(1));
        assertEquals("0", test.get(2));

    }
    @Test
    void format1() {

        ArrayList<String> test = new ArrayList<>();
        test = main.format(b.getVersion());
        assertNotNull(test);
        assertEquals(3,test.size());
        assertEquals("1", test.get(0));
        assertEquals("0", test.get(1));
        assertEquals("0", test.get(2));

    }
    @Test
    void formatalpha3() {

        ArrayList<String> test = new ArrayList<>();
        test = main.format(a1.getVersion());
        assertNotNull(test);
        assertEquals(3,test.size());
        assertEquals("1", test.get(0));
        assertEquals("1", test.get(1));
        assertEquals("1", test.get(2));

    }
    @Test
    void formatalpha2() {

        ArrayList<String> test = new ArrayList<>();
        test = main.format(c1.getVersion());
        assertNotNull(test);
        assertEquals(3,test.size());
        assertEquals("1", test.get(0));
        assertEquals("1", test.get(1));
        assertEquals("0", test.get(2));

    }
    @Test
    void formatalpha1() {

        ArrayList<String> test = new ArrayList<>();
        test = main.format(b1.getVersion());
        assertNotNull(test);
        assertEquals(3,test.size());
        assertEquals("1", test.get(0));
        assertEquals("0", test.get(1));
        assertEquals("0", test.get(2));

        assertEquals(test, main.format(b1.getVersion()));
    }

    @Test
    void compareAGreaterB() {
        main.compare(main.format(a.getVersion()), main.format(b.getVersion()), a, b);
        assertEquals(a.getVersion()+" greater than "+b.getVersion(), main.compare(main.format(a.getVersion()), main.format(b.getVersion()), a, b));
    }
    @Test
    void compareCGreaterB() {
        main.compare(main.format(c.getVersion()), main.format(b.getVersion()), b, c);
        assertEquals(c.getVersion()+" greater than "+b.getVersion(), main.compare(main.format(c.getVersion()), main.format(b.getVersion()), c, b));
    }

    @Test
    void compareLower() {
        main.compare(main.format(b.getVersion()), main.format(a.getVersion()), b, a);
        assertEquals(b.getVersion()+" less than "+a.getVersion(), main.compare(main.format(b.getVersion()), main.format(a.getVersion()), b, a));
    }
    @Test
    void compareEqualsSame(){
        main.compare(main.format(b.getVersion()), main.format(b.getVersion()), b, b);
        assertEquals(b.getVersion()+" is equal to "+b.getVersion(), main.compare(main.format(b.getVersion()), main.format(b.getVersion()), b, b));
    }
    @Test
    void compareEqualsDiff(){
        main.compare(main.format(b.getVersion()), main.format(b1.getVersion()), b, b1);
        assertEquals(b.getVersion()+" is equal to "+b1.getVersion(), main.compare(main.format(b.getVersion()), main.format(b1.getVersion()), b, b1));
    }



}