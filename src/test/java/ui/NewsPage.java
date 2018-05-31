package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewsPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(NewsPage.class);

    @FindBy(id = "comment_body")
    private WebElement textarea;
    @FindBy(xpath = "//button[text()='Отправить']")
    private WebElement btnSent;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public int countItemMessage() {
        LOG.info("countItemMessage");
        return driver.findElements(By.xpath("//div[@class='ne-message']"))
                .size();
    }

    public Page sentMessange(String text) {
        LOG.info("sentMessange: "+text);
        actions.sendKeys(textarea, text)
                .click(btnSent)
                .build().perform();
        return this;
    }

    public boolean isMessangePresent(String text) {
        try {
            boolean isDisplayed=wait.until(webDriver -> webDriver.findElement(
                    By.xpath("//div[@class='ne-message' and .//div[text()='" + text
                            + "'] and .//time[text()='меньше минуты назад']]"))
                    .isDisplayed());
            LOG.warn("isMessangePresent "+text+" "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("isMessangePresent "+text+" false");
            return false;
        }
    }

    public boolean isNewsPage() {
        try {
            wait.until(ExpectedConditions.urlContains("news"));
            LOG.warn("isNewsPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isNewsPage false");
            return false;
        }
    }
}
