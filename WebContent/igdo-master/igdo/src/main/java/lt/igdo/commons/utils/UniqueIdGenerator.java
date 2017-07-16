/**
 * UniqueIdGenerator.java
 * Created: Oct 23, 2007 10:49:01 PM
 */
package lt.igdo.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.log4j.Logger;

/**
 * Generator of unique user ids.
 * 
 * @author Donatas
 * 
 */
public final class UniqueIdGenerator {

    /** Secure random number generator. */
    private static SecureRandom secureRandom;

    /** Logger. */
    private static Logger logger = Logger.getLogger(UniqueIdGenerator.class);

    /** Characters representing hexadecimal bytes. */
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * No need to initialize utilis.
     */
    private UniqueIdGenerator() {

    }

    /**
     * Generate unique identifier.
     * 
     * @return Generated identifier.
     */
    public synchronized static String getUniqueId() {
        byte[] result = {};
        try {
            secureRandom = UniqueIdGenerator.getSecureRandom();
            String randomNum = new Integer(secureRandom.nextInt()).toString();
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            result = sha.digest(randomNum.getBytes());
        } catch (NoSuchAlgorithmException e) {
            logger.error("Failed to generate appropriate unique identificator",
                    e);
        }
        // Add miliseconds in case something goes wrong.
        String generatedId = hexEncode(result) + System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("Unique identificator generated:" + generatedId);
        }
        return generatedId;
    }

    /**
     * Lazily initialize secure numbers generator.
     * 
     * @return SecureRandom instance.
     * @throws NoSuchAlgorithmException
     */
    private synchronized static SecureRandom getSecureRandom()
            throws NoSuchAlgorithmException {
        if (secureRandom == null) {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        }
        return secureRandom;
    }

    /**
     * Encode unique byte set to a String.
     * 
     * @param bytes
     *            Byte array containing unique number.
     * @return String representation of the unique number.
     */
    static private String hexEncode(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            result.append(DIGITS[(b & 0xf0) >> 4]);
            result.append(DIGITS[b & 0x0f]);
        }
        return result.toString();
    }
}
