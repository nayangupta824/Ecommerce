/**
 * ServiceLocator.java
 * Created: Apr 27, 2008 10:57:13 PM
 */
package lt.igdo.commons.utils;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

/**
 * Service locator implementation.
 * 
 * @author Donatas
 * 
 */
public final class ServiceLocator {

    /** Logger. */
    private static Logger logger = Logger.getLogger(ServiceLocator.class);

    /**
     * Service locator method to lookup ejbs from glassfish server.
     * 
     * @param name
     *            Name of the service.
     * @return Service instance.
     */
    public static Object lookup(String name) {
        try {
            java.util.Properties props = new java.util.Properties();
            props.setProperty("java.naming.factory.initial",
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs",
                    "com.sun.enterprise.naming");
            props
                    .setProperty("java.naming.factory.state",
                            "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            Context context = new InitialContext(props);

            return context.lookup(name);

        } catch (Exception e) {
            ServiceLocator.logger.error("Error while looking up " + name, e);
            e.printStackTrace();
        }
        return null;
    }

    /* JBoss lookup */
    // public static Object lookup(String name) {
    // try {
    // Hashtable<String, String> env = new Hashtable<String, String>();
    //
    // env.put(Context.INITIAL_CONTEXT_FACTORY,
    // "org.jnp.interfaces.NamingContextFactory");
    // env.put(Context.URL_PKG_PREFIXES,
    // "org.jboss.naming:org.jnp.interfaces");
    // env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
    //
    // logger.debug("Looking up for service " + name);
    // Context context = new InitialContext(env);
    //
    // return context.lookup(name);
    // } catch (Exception e) {
    // logger.error("Error while looking up " + name, e);
    // e.printStackTrace();
    // }
    // return null;
    // }
}
