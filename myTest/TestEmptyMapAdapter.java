//Diego Chiesurin - 2111553

package myTest;

import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import myAdapter.*;

public class TestEmptyMapAdapter {

    @Test
    public void testEmptyMapAdapter() {
        // Create an instance of the adapter
        MapAdapter adapter = new MapAdapter();

        // Test the adapter's behavior
        assertTrue(adapter.isEmpty());
        assertEquals(0, adapter.size());
    }
}
