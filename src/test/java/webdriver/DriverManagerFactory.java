package webdriver;

import exceptions.UnknownDriverTypeException;

import java.util.HashMap;

public class DriverManagerFactory {
    private static HashMap<DriverType, DriverManager> instances;

    private DriverManagerFactory() { }

    static {
        instances = new HashMap<>();
    }

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        if (!instances.containsKey(type)) {
            switch (type) {
                case CHROME:
                    driverManager = new ChromeDriverManager();
                    break;
                case FIREFOX:
                    driverManager = new FirefoxDriverManager();
                    break;
                default:
                    throw new UnknownDriverTypeException(type.name());
            }
            instances.put(type, driverManager);
        } else {
            driverManager = instances.get(type);
        }
        return driverManager;

    }
}