package myTest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import myAdapter.*;
import myExceptions.IllegalStateException;

public class TestMapAdapterIterators {

    private HMap map;
    
    public TestMapAdapterIterators() {
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

    /*------------------------- EntrySetIterator -------------------------*/
    @Test
    public void testEntrySetIteratorOnEmptyCollection() {
        HIterator iterator = map.entrySet().iterator();
        assertFalse("Iterator on an empty collection should have no elements", iterator.hasNext());
    }

    @Test
    public void testEntrySetIteratorOnPopulatedCollection() {
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + i);
        }
        HIterator iterator = map.entrySet().iterator();
        assertTrue("Iterator on a populated collection should have elements", iterator.hasNext());
    }
}
