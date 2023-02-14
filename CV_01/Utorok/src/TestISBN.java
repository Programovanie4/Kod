import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestISBN {
@Test
    public void testIsbn() {
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("9781978104471"));

        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 0-19-852663-6"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 1-86197-271-7"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN - 9781861972712"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1-61729-494-2"));

        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN-13: 978-1-61729-254-5"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 1-61729-254-0"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN-13: 978-1-61729-120-3"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 1-61729-120-X"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 1-61729-120-x"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN-13: 978-1-68050-725-6"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 1-68050-725-7"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN-13: 978-1-935182-35-1"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 1-935182-35-8"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN-13: 978-1-934356-40-1"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 1-934356-40-9"));


        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN - 9781681972712")); // swapped
        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN - 9781861973712"));  // misread

        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 0-8218-3933-0"));
        assertTrue("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 9781795416955"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 978-1-61729-757-1"));

        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 0-821-3933-0"));
        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 9781X95416955"));
        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 978-1-61739-757-1"));

        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 0-8218/3933-0"));
        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN 978179541216955"));
        assertFalse("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("IBSN 978-1-61729-757-1"));


        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 9788026603665"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 978-80-206-1743-9"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("979-8821535474"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1978104471"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1978104472"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-0596009205"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("0596009208"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1593279280"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1593279288"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1945051752"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1945051753"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("979-8590288045"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1943704279"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1943704279"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("979-8590288045"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn(" 978-6075686424"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("6075686428"));

        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 9788036603665"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("ISBN: 978-80-306-1743-9"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("979-8831535474"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1978104471"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1978104473"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-0596009305"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("0596009308"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1593379380"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1593379388"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1945051753"));
        assertTrue ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("9781945051753"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("979-8590388045"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("978-1943704379"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("1943704379"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("979-8590388045"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn(" 978-6075686434"));
        assertFalse ("ak nevies, ktory test padne, vypis si argumenty funkcie sam",ISBN.isbn("6075686438"));

    }
}