/**
 * TestPasswordUtils.java
 * Created: Feb 16, 2008 10:40:17 AM
 */
package lt.igdo.commons.utils;

import org.junit.Test;

/**
 * Tests for {@link PasswordUtils} class.
 * 
 * @author Donatas
 * 
 */
public class TestPasswordUtils {

    /**
     * Tests if hash works correctly.
     */
    @Test
    public void testPasswordHashing() {
//        String firstPasswordToHash = "passwordToHash";
//        String firstResult = PasswordUtils.hashPassword(firstPasswordToHash);
//
//        String secondResult = PasswordUtils.hashPassword("password" + "ToHash");
//
//        Assert.assertNotNull(firstResult, "Hashed password can not be null");
//        Assert.assertNotNull(secondResult, "Hashed password can not be null");
//
//        Assert.assertEquals(firstResult, secondResult,
//                "Hash for the same strings must be equal");
    }

    /**
     * Tests if null is returned if null passed as parameter.
     */
    @Test
    public void testPasswordHashingWithNullPassword() {
//        String result = PasswordUtils.hashPassword(null);
//        Assert.assertNull(result, "Result must be null");
    }
}
