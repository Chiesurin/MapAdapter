//Diego Chiesurin - 2111553

package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestEmptyMapAdapter.class,
    TestEmptyAbstractCollections.class,
    TestPopulatedMapAdapter.class
})
public class TestsSuite {
    
    public TestsSuite() {
        // intentionally left blank
    }
}