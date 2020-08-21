import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerStringTest {
    ServerString a = null;
    ServerString b = null;
    ServerString c = null;
    ServerString a1 = null;
    ServerString b1 = null;
    ServerString c1 = null;
    ServerString invalid = null;
    ServerString invalid1 = null;
    ServerString invalid2 = null;

    @BeforeEach
    void setUp() {
        a = new ServerString("1.1.1");
        b = new ServerString("1");
        c = new ServerString("1.1");
        a1 = new ServerString("1.1.1-alpha");
        b1 = new ServerString("1-alpha");
        c1 = new ServerString("1.1-alpha");
        invalid = new ServerString("1.1.1.1");
        invalid1 = new ServerString("1.1.1.a");
        invalid2 = new ServerString(".1.1.1");
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void valid2() {
        assertTrue(a.valid2(a.getVersion()));
        assertTrue(b.valid2(b.getVersion()));
        assertTrue(c.valid2(c.getVersion()));
        assertTrue(a1.valid2(a1.getVersion()));
        assertTrue(b1.valid2(b1.getVersion()));
        assertTrue(c1.valid2(c1.getVersion()));
    }

    @Test
    void valid2Invalid(){
        assertFalse(invalid.valid2(invalid.getVersion()));
    }
}