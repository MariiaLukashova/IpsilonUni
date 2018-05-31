package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Page {
    private static final Logger LOG = Logger.getLogger(Page.class);

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    public Page(WebDriver driver) {
        if (this.driver == null) {
            this.driver = driver;
            this.actions = new Actions(this.driver);
            this.wait = new WebDriverWait(driver, 30, 1);
            PageFactory.initElements(this.driver, this);
        }
    }

    public Page openWebSite() {
        driver.manage().window().maximize();
        driver.get("http://ipsilon-dev.sgu.ru/");
        LOG.info("openWebSite");
        return new LoginPage(this.driver);
    }

    public void close() {
        LOG.info("close");
        driver.quit();
        driver = null;
    }

    protected boolean isElementPresent(WebElement element){
        try {
            LOG.warn("isElementPresent "+element.toString()+" "+element.isDisplayed());
            return element.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isElementPresent "+element.toString()+" false");
            return false;
        }
    }
    protected void waitIsNotElementPresent(WebElement element){
        try{
            wait.until(webDriver -> !element.isDisplayed());
        }catch (RuntimeException e){
        }
    }
    protected void waitIsElementPresent(WebElement element){
        try{
            wait.until(webDriver -> element.isDisplayed());
        }catch (RuntimeException e){
        }
    }
}
