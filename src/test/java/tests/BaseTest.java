package tests;

import businessobject.User;
import config.ScreenListener;
import config.Screenshot;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.uncommons.reportng.HTMLReporter;
import ui.LoginPage;
import ui.MainPage;
import ui.Page;
import webdriver.DriverManager;
import webdriver.DriverManagerFactory;
import webdriver.DriverType;

public class BaseTest {
    private static final Logger LOG = Logger.getLogger(BaseTest.class);
    protected static Page page;
    DriverManager driverManager;

    public WebDriver getDriver() {
        return page.getDriver();
    }

    @BeforeSuite
    public void startBrowser() {
        LOG.info("startBrowser");
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        page = new Page(driverManager.getDriver());

    }
    @BeforeClass
    public void startWebSite() {
        LOG.info("startWebSite");
        page.openWebSite();
        Assert.assertTrue(new LoginPage(page.getDriver()).isLoginPage());
    }
    @BeforeClass
    public void x_Login() {
        LOG.info("x_Login");
        new LoginPage(page.getDriver()).login(new User("mari-lukashowa@yandex.ru", "password"));
        Assert.assertTrue(new MainPage(page.getDriver()).isLogin("Лукашова М. А."));
    }
    @AfterClass
    public void authorizationOut() {
        LOG.info("closeAuthorizationOut");
        new MainPage(page.getDriver()).logout();
        Assert.assertTrue(new LoginPage(page.getDriver()).isLoginPage(), "The logout didn't occurred");
    }
    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        LOG.info("closeBrowser");
        driverManager.quitDriver();
        page.close();
    }

}
