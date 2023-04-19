import org.junit.Test;

import static org.junit.Assert.*;
public class TestStreamy {

    @Test
    public void all11() {

        for(int i = 0; i < 1_000_000; i++)
            if (Streamy.div11.test(Streamy.iterate(Streamy.cifRozdiel).apply(i)) != (i%11==0))
                fail("div11 fails: " + i);
    }

    @Test
    public void all3() {
        for(int i = 0; i < 1_000_000; i++)
            if (Streamy.div3.test(Streamy.iterate(Streamy.cifSucet).apply(i)) != (i%3==0))
                fail("div3 fails: " + i);
    }

    @Test
    public void all9() {
        for(int i = 0; i < 1_000_000; i++)
            if (Streamy.div9.test(Streamy.iterate(Streamy.cifSucet).apply(i)) != (i%9==0))
                fail("div9 fails: " + i);
    }

}