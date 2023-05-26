import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void testEveryBranch() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> SILab2.function(null, null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        assertFalse(SILab2.function(new User("A", "A", "B"), null));
        assertFalse(SILab2.function(
                new User(null, "pass#word", "A@email.com"),
                new ArrayList<User>(Arrays.asList(
                        new User("Unique", "valid!Pass", "B@gmail.com"),
                        new User("A@email.com", "random!Pass", "A@email.com")
        ))));
        assertFalse(SILab2.function(new User("a", "bcdef ghi", "a-"), null));
        assertFalse(SILab2.function(new User("a", "bcdefghi", "a-"), null));
    }

    /**
     * Testing Multiple Conditions on the following if statement in SILab2.java:
     * if (user==null || user.getPassword()==null || user.getEmail()==null)
     */
    @Test
    void testMultipleConditions() {
        RuntimeException e = assertThrows(
                RuntimeException.class,
                () -> SILab2.function(null, null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        e = assertThrows(
                RuntimeException.class,
                () -> SILab2.function(new User("abc", null, "abc"), null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        e = assertThrows(
                RuntimeException.class,
                () -> SILab2.function(new User("abc", "abc", null), null));
        assertTrue(e.getMessage().contains("Mandatory information missing!"));
        assertFalse(SILab2.function(new User("abc", "abc", "abc"), null));
    }

}