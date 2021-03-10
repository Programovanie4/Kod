import org.junit.Test;

import static org.junit.Assert.*;

public class TestPriemer {

    @Test
    public void priemer() {
        assertEquals("1+2+3", 2.0,
                Priemer.priemer(new String[]{"1","2","3"}), 0.001);
        assertEquals("1+2+3", 15.0,
                Priemer.priemer(new String[]{"10","20"}), 0.001);
    }
}