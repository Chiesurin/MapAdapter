package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import myExceptions.UnsupportedOperationException;
import org.junit.After;

public class TestPopulatedMapAdapter {

    private static final int MAP_INITIAL_CAPACITY = 100;
    private HMap map;

    public TestPopulatedMapAdapter() {
        // intentionally left blank
    }

    @Before
    public void setUp() {
        this.map = new MapAdapter(MAP_INITIAL_CAPACITY);
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            this.map.put("testKey" + i, "testValue" + i);
        }
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    @Test
    public void testCopyConstuctorOnPopulatedMap() {
        HMap copy = new MapAdapter(this.map);
        assertEquals("Copy constructor should create an identical map", this.map, copy);
        assertEquals("Size of copied map should match original", this.map.size(), copy.size());
    }

    @Test
    public void clear() {
        this.map.clear();
        assertTrue("MapAdapter should be empty after clear", this.map.isEmpty());
        assertEquals("Size of map should be 0 after clear", 0, this.map.size());
    }

    @Test
    public void testContainsKeyOnPopulatedMapAdapter(){
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain every key inserted", this.map.containsKey("testKey" + i));
        }

        assertFalse("MapAdapter should not contain a key that is not contained", this.map.containsKey("nonExistentKey"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsKeyOnMapAdapterWithNullKey() {
        this.map.containsKey(null);
    }

    @Test
    public void testContainsValueOnPopulatedMapAdapter() {
        for(int i = 0; i < MAP_INITIAL_CAPACITY; i++){
            assertTrue("MapAdapter should contain every value inserted", this.map.containsValue("testValue" + i));
        }

        assertFalse("MapAdapter should not contain a value that is not contained", this.map.containsValue("nonExistentValue"));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsValueOnPopulatedMapAdapterWithNullValue() {
        this.map.containsValue(null);
    }


    @Test
    public void testToStringOnPopulatedMapAdapter() {
        HIterator iterator = this.map.entrySet().iterator();
        String expected = "{";
        while(iterator.hasNext()) {
            HMap.HEntry entry = (HMap.HEntry) iterator.next();
            expected += entry.toString() + (iterator.hasNext() ? ", " : "}");
        }

        assertEquals("toString() should return a string representation of the map created this way", expected, this.map.toString());
    }
}