/**
 * PersistenceUtils.java
 * Created: Feb 12, 2008 7:14:30 PM
 */
package lt.igdo.commons.utils;

import java.util.List;

import lt.igdo.commons.exceptions.fatal.UnrecoverableApplicationException;

/**
 * Persistence utilities.
 * 
 * @author Donatas
 * 
 */
public class PersistenceUtils {

    /**
     * No need to create util instance.
     */
    private PersistenceUtils() {

    }

    /**
     * Extract single result from the list. Throw business exception if more
     * than one result exist.
     * 
     * @param list
     *            Result list.
     * @return Single result.
     */
    public static Object getSingleResult(@SuppressWarnings("rawtypes") List list) {
        if (list == null) {
            return null;
        }
        if (list.size() > 1) {
            throw new UnrecoverableApplicationException();
        } else if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
