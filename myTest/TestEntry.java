//Diego Chiesurin - 2111553

package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.util.Stack;
import myExceptions.UnsupportedOperationException;
import org.junit.After;

public class TestEntry {
    private HMap map;

    public TestEntry() {
        //intentionally left empty
    }

    @Before
    public void setUp() {
        this.map = new MapAdapter();
        this.map.put("testKey", "testValue");
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    @Test
    public void testEqualsOnEntry() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        assertTrue("An Entry should be equal to another Entry with the same key and value", 
                   ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithNull() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        assertFalse("An Entry should not be equal to null", entry.equals(null));
    }

    @Test
    public void testEqualsOnEntryWithDifferentValue() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "differentValue");
        assertFalse("An Entry should not be equal to another Entry with the same key but different value", 
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithDifferentKey() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("differentKey", "testValue");
        assertFalse("An Entry should not be equal to another Entry with a different key",
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullKey() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, "testValue");
        assertFalse("An Entry should not be equal to another Entry with a null key",
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put("testKey", null);
        assertFalse("An Entry should not be equal to another Entry with a null value",
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithNullKeyAndValue() {
        HMap anotherMap = new DummyTestMap();
        anotherMap.put(null, null);
        assertFalse("An Entry should not be equal to another Entry with both key and value as null",
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithItself() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        assertTrue("An Entry should be equal to itself", entry.equals(entry));
    }

    @Test
    public void testEqualsOnEntryIsSymmetric() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        HMap.HEntry anotherEntry = (HMap.HEntry) anotherMap.entrySet().iterator().next();
        assertTrue("Equals should be symmetric", entry.equals(anotherEntry) && anotherEntry.equals(entry));
    }

    @Test
    public void testEqualOnEntryIsTransitive() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", "testValue");
        HMap yetAnotherMap = new MapAdapter();
        yetAnotherMap.put("testKey", "testValue");
        
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        HMap.HEntry anotherEntry = (HMap.HEntry) anotherMap.entrySet().iterator().next();
        HMap.HEntry anotherAnotherEntry = (HMap.HEntry) yetAnotherMap.entrySet().iterator().next();

        assertTrue("If Entry A equals Entry B and Entry B equals Entry C, then Entry A should equal Entry C",
                   entry.equals(anotherEntry) && anotherEntry.equals(anotherAnotherEntry) && entry.equals(anotherAnotherEntry));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithDifferentTypeOfKey() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put(1, "testValue");
        assertFalse("An Entry should not be equal to another Entry with a different type of key",
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test
    public void testEqualsOnEntryWithAnotherEntryWithDifferentTypeOfValue() {
        HMap anotherMap = new MapAdapter();
        anotherMap.put("testKey", 1);
        assertFalse("An Entry should not be equal to another Entry with a different type of value",
                    ((HMap.HEntry) this.map.entrySet().iterator().next()).equals((HMap.HEntry) anotherMap.entrySet().iterator().next()));
    }

    @Test(expected = StackOverflowError.class)
    public void testEqualsOnEntryWithEntryContainingThisEntryAsAValue() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        entry.setValue(entry); //does not cause stack overflow
        entry.equals(entry); //causes stack overflow
    }

    @Test
    public void testGetKeyOnEntry() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        assertEquals("Get key should return the correct key", "testKey", entry.getKey());
    }

    @Test
    public void testGetValueOnEntry() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        assertEquals("Get value should return the correct value", "testValue", entry.getValue());
    }

    @Test
    public void testHashCodeOnEntry() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        int expectedHashCode = (entry.getKey().hashCode() ^ entry.getValue().hashCode());
        assertEquals("Hash code should be computed as key's hash code XOR value's hash code", expectedHashCode, entry.hashCode());
    }

    @Test(expected = StackOverflowError.class)
    public void testHashCodeOnEntryWithEntryContainingThisEntryAsAValue() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        entry.setValue(entry); //does not cause stack overflow
        entry.hashCode(); //causes stack overflow
    }

    @Test
    public void testSetValueOnEntry() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        entry.setValue("newValue");
        assertEquals("Set value should update the value in the entry", "newValue", entry.getValue());
        assertEquals("Map should reflect the updated value", "newValue", this.map.get("testKey"));
        assertTrue("MapAdapter should contain the updated value", this.map.containsValue("newValue"));
    }

    @Test(expected = NullPointerException.class)
    public void testSetValueOnEntryWithNullValue() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        entry.setValue(null);
    }

    @Test
    public void testToStringOnEntry() {
        HMap.HEntry entry = (HMap.HEntry) this.map.entrySet().iterator().next();
        String expectedString = "testKey=testValue";
        assertEquals("ToString should return the key-value pair in the format 'key=value'", expectedString, entry.toString());
    }   
}