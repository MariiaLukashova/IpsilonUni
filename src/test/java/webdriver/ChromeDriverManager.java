package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.log4testng.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {
    private static final Logger LOG = Logger.getLogger(ChromeDriverManager.class);

    private ChromeDriverService chService;

    @Override
    public void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/test/resources/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
        WebDriver regularDriver = new ChromeDriver(chService);
        regularDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver=new EventFiringWebDriver(regularDriver).register(new WebDriverLogger());
    }

}
