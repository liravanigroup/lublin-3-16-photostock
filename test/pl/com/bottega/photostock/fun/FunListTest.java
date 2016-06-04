package pl.com.bottega.photostock.fun;

import org.junit.Test;
import pl.com.bottega.fun.EmptyList;
import pl.com.bottega.fun.FunList;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by maciuch on 04.06.16.
 */
public class FunListTest {

    @Test
    public void shouldAddElements() {
        //given
        FunList<String> l = new EmptyList<>();

        //when
        l = l.add("first").add("second").add("third").add("fifth");

        // then
        assertTrue(l.contains("first"));
        assertTrue(l.contains("third"));
    }

    @Test
    public void shouldReturnStringRepresentation() {
        //given
        FunList<String> l = new EmptyList<>();

        //when
        l = l.add("first").add("second").add("third").add("fifth");

        // then
        assertEquals("first, second, third, fifth", l.toString());
    }

    @Test
    public void shouldReturnSize() {
        //given
        FunList<String> l = new EmptyList<>();

        //when
        l = l.add("first").add("second").add("third").add("fifth");

        // then
        assertEquals(4, l.size());
    }

    @Test
    public void shouldGetElement() {
        //given
        FunList<String> l = new EmptyList<>();

        //when
        l = l.add("first").add("second").add("third").add("fifth");

        //then
        assertNull(l.get(-1));
        assertNull(l.get(10000000));
        assertEquals("second", l.get(1));
    }

}
