//Diego Chiesurin - 2111553

package myTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import myAdapter.*;
import myExceptions.IllegalStateException;
import java.util.NoSuchElementException;

public class TestEmptyAbstractCollectionIterators {
    
    private HMap map;

    public TestEmptyAbstractCollectionIterators() {
        // intentionally left empty
    }

    @Before
    public void setUp() {
        this.map = new MapAdapter();
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- EntrySetIterator -------------------------*/
    /*--------------------------------------------------------------------*/

    @Test
    public void testHasNextOnEmptyEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        assertFalse("Iterator on an empty collection should not have elements", iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyEntrySetIterator() {
        HIterator iterator = map.entrySet().iterator();
        iterator.remove();
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- KeySetIterator ----------------------------*/
    /*--------------------------------------------------------------------*/

    @Test
    public void testHasNextOnEmptyKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        assertFalse("Iterator on an empty collection should not have elements", iterator.hasNext());    
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyKeySetIterator() {
        HIterator iterator = map.keySet().iterator();
        iterator.remove();
    }

    /*--------------------------------------------------------------------*/
    /*------------------------- ValuesIterator ---------------------------*/
    /*--------------------------------------------------------------------*/

    @Test
    public void testHasNextOnEmptyValuesIterator() {
        HIterator iterator = map.values().iterator();
        assertFalse("Iterator on an empty collection should not have elements", iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextOnEmptyValuesIterator() {
        HIterator iterator = map.values().iterator();
        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyValuesIterator() {
        HIterator iterator = map.values().iterator();
        iterator.remove();
    }
}
