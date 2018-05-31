package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;

import java.util.concurrent.TimeUnit;

public class FirefoxDriverManager extends DriverManager {
    private static final Logger LOG = Logger.getLogger(ChromeDriverManager.class);


    @Override
    public void startService() {

    }

    @Override
    public void stopService() {
    }

    @Override
    public void createDriver() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver=new EventFiringWebDriver(driver).register(new AbstractWebDriverEventListener() {
            @Override
            public void beforeClickOn(WebElement element, WebDriver driver) {
                LOG.info("Click on element "+element.getTagName());
            }
        });
    }

}
