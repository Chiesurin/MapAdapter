//Diego Chiesurin - 2111553

package myTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import junit.framework.Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestEmptyMapAdapter.class,
    TestEmptyAbstractCollections.class,
    TestPopulatedMapAdapter.class,
    TestEntry.class,
    TestPopulatedAbstractCollections.class,
    TestPopulatedAbstractCollectionIterators.class,
    TestEmptyAbstractCollectionIterators.class
})
public class TestsSuite {
    
    public TestsSuite() {
        // intentionally left blank
    }
}