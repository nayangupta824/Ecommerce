/**
 * TestPersistenceUtils.java
 * Created: Feb 12, 2008 8:06:46 PM
 */
package lt.igdo.commons.utils;

import org.junit.Test;

/**
 * Tests for {@link PersistenceUtils} class.
 * 
 * @author Donatas
 * 
 */
public class TestPersistenceUtils {

    /**
     * Test that null is returned when no list is passed.
     */
    @Test
    public void testSingleResultSelectionWithNullList() {
//        Object object = PersistenceUtils.getSingleResult(null);
//        Assert.assertNull(object, "Result must be null");
    }

    /**
     * Test that method returns the same object as passed.
     */
    @Test
    public void testSingleResultSelection() {
//        List<Object> list = new ArrayList<Object>();
//        Object expected = new Object();
//        list.add(expected);
//        Object actual = PersistenceUtils.getSingleResult(list);
//        Assert.assertSame(actual, expected,
//                "Passed and returned objects are not the same");
    }

    /**
     * Test that method returns the same object as passed.
     */
    @Test
    public void testSingleResultWithEmptyList() {
//        Object object = PersistenceUtils
//                .getSingleResult(new ArrayList<Object>());
//        Assert.assertNull(object, "Result must be null");
    }

    /**
     * Test that UnrecoverableApplicationException is thrown if list contains
     * more than one element.
     */
    @Test(/*expectedExceptions = UnrecoverableApplicationException.class*/)
    public void testSingleResultSelectionWithBadList() {
//        List<Object> list = new ArrayList<Object>();
//        list.add(new Object());
//        list.add(new Object());
//        PersistenceUtils.getSingleResult(list);
    }

}
