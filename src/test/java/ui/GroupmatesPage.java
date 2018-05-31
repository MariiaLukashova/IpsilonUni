package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupmatesPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(GroupmatesPage.class);

    @FindBy(xpath = "//div[@class='us-user']")
    private WebElement itemUser;

    public GroupmatesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isGroupmatesListPresent() {
        try {
            LOG.warn("isGroupmatesListPresent "+itemUser.isDisplayed());
            return itemUser.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isGroupmatesListPresent false");
            return false;
        }
    }

    public boolean writeLetter() {
        LOG.info("writeLetter");
        String fio = itemUser.findElement(By.xpath("//div[@class='us-user-info-fio']")).getText();
        itemUser.findElement(By.xpath("//div[@class='us-user-action']/a")).click();
        LOG.info(fio);
        wait.until(webDriver -> new MessagesPage(webDriver).isMessagesPage());
        return new MessagesPage(driver).isOpenDialogueWith(fio);
    }

    public boolean isGroupmatesPage() {
        try {
            wait.until(ExpectedConditions.urlContains("users"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Одногруппники' and @class='active']")));
            LOG.warn("isGroupmatesPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isGroupmatesPage false");
            return false;
        }
    }
}
