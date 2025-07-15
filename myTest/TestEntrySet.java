//Diego Chiesurin - 2111553

package myTest;

import org.junit.Test;

import myAdapter.HSet;

import static org.junit.Assert.*;
import org.junit.Before;

public class TestEntrySet {

    private HSet entrySet;

    public TestEntrySet() {
        // intentionally left blank
    }

    @Before
    public void setUp() {
        
        // Add some entries to the entrySet for testing
        entrySet.add("Entry1");
        entrySet.add("Entry2");

    }

    
}
