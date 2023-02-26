import org.junit.Test;

import static org.junit.Assert.*;

public class PodobneTest {

    @Test
    public void testPodobne() {
        assertTrue("test 1", Podobne.podobne(null, null));
        assertFalse("test 2", Podobne.podobne(new String[]{}, null));
        assertTrue("test 3", Podobne.podobne(new String[]{"aa", null, ""}, new Integer[] {2,null,0}));
    }
}