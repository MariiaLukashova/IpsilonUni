package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MessagesPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(MessagesPage.class);

    @FindBy(xpath = "//a[text()='Добавить диалог']")
    private WebElement addDialogue;
    @FindBy(xpath = "//div[@class='ch-contact']")
    private WebElement itemContact;
    @FindBy(id = "js-dialogs_message_body")
    private WebElement textareaMessage;
    @FindBy(id = "js-send_message_from_form")
    private WebElement btnSent;
    @FindBy(xpath = "//div[@class='ch-message js-message']")
    private WebElement itemMessange;

    public MessagesPage(WebDriver driver) {
        super(driver);
    }

    public Page addDialogue() {
        LOG.info("addDialogue");
        addDialogue.click();
        wait.until(webDriver -> new GroupmatesPage(webDriver).isGroupmatesPage());
        return new GroupmatesPage(this.driver);
    }

    public Page openDialogue() {
        LOG.info("openDialogue");
        itemContact.click();
        wait.until(ExpectedConditions.visibilityOf(textareaMessage));
        return this;
    }

    public Page sentMessage(String text) {
        actions.sendKeys(textareaMessage, text)
                .click(btnSent)
                .build().perform();
        LOG.info("sentMessage "+text);
        return this;
    }

    public boolean isMessangePresent(String text) {
        try {
            boolean isDisplayed=wait.until(webDriver -> webDriver.findElement(
                By.xpath("//div[@class='ch-message js-message' and ./div[text()='" + text
                        + "'] and .//time[text()='меньше минуты назад']]"))
                .isDisplayed());
            LOG.warn("isMessangePresent "+text+" "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("isMessangePresent "+text+" false");
            return false;
        }
    }

    public boolean isOpenDialogueWith(String fio) {
        try {
            boolean isDisplayed=driver.findElement(
                By.xpath("//a[@class='active']//div[@class='ch-contact-fio']"))
                .isDisplayed();
            LOG.warn("isOpenDialogueWith "+fio+" "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("isOpenDialogueWith "+fio+"false");
            return false;
        }
    }

    public boolean isMessagesPage() {
        try {
            wait.until(ExpectedConditions.urlContains("dialogs"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Сообщения' and @class='active']")));
            LOG.warn("isMessagesPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isMessagesPage false");
            return false;
        }
    }
}
