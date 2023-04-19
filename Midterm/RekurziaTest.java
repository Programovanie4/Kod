import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RekurziaTest {

    @Test
    public void testfoo1() {
        for(var p : List.of(2,3,5,7,11)) {
            for (int a = 0; a < 15; a++) {
                for (int b = a; b < 15; b++)
                    assertEquals("foo1", Rekurzia.foo(a, b, p), Rekurzia.foo1(a, b, p));
                System.out.println();
            }
        }
    }

    @Test
    public void testfoo2() {
        for(var p : List.of(2,3,5,7,11)) {
            for (int a = 0; a < 190; a++) {
                for (int b = a; b < 190; b++)
                    assertEquals("foo2", Rekurzia.foo1(a, b, p), Rekurzia.foo2(a, b, p));
                System.out.println();
            }
        }
    }

    @Test
    public void testfoo3() {
        for(var p : List.of(2,3,5,7,11)) {
            for (int a = 0; a < 190; a++) {
                for (int b = a; b < 190; b++)
                    assertEquals("foo3", Rekurzia.foo2(a, b, p), Rekurzia.foo3(a, b, p));
                System.out.println();
            }
        }
    }


}