package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class SupportPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(SupportPage.class);

    @FindBy(id = "support_question_body")
    private WebElement textarea;
    @FindBy(xpath = "//a[@class='add_fields']")
    private WebElement addFile;
    @FindBy(xpath = "//input[@type='file']")
    private WebElement chooseFile;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnSent;
    @FindBy(xpath = "//div[@class='su-question']")
    private WebElement itemQuestion;


    public SupportPage(WebDriver driver) {
        super(driver);
    }

    public int countItemQuestion() {
        LOG.info("countItemQuestion");
        return driver.findElements(By.xpath("//div[@class='su-question']"))
                .size();
    }

    public Page sentQuestion(String text) {
        actions.sendKeys(textarea, text)
                .click(btnSent)
                .build().perform();
        LOG.info("sentQuestionWithoutFile "+text);
        return this;
    }

    public Page sentQuestion(String text, String pathFile) {
        actions.click(addFile).build().perform();
        chooseFile.sendKeys(new File("").getAbsolutePath()+pathFile);
        actions.sendKeys(textarea, text)
                .click(btnSent)
                .build().perform();
        LOG.info("sentQuestionWithFile "+text+" - "+pathFile);
        return this;
    }
    public boolean isQuestionPresent(String text) {
        try {
            boolean isDisplayed=wait.until(webDriver -> webDriver.findElement(
                    By.xpath("//div[@class='su-question' and .//div[text()='" + text
                            + "'] and .//time[text()='меньше минуты назад']]"))
                    .isDisplayed());
            LOG.warn("isQuestionPresent "+text+" "+isDisplayed+
                " "+new SupportPage(driver).isSupportPage());
            return isDisplayed && new SupportPage(driver).isSupportPage();
        } catch (RuntimeException e) {
            LOG.warn("isQuestionPresent "+text+" false");
            return false;
        }
    }
    public boolean isQuestionPresent(String text, String nameFile) {
        try {
            boolean isDisplayed=wait.until(webDriver -> webDriver.findElement(
                By.xpath("//div[@class='su-question' and .//div[text()='" + text
                        + "'] and .//time[text()='меньше минуты назад']  and .//div[@class='b-file']//a[contains(@href,'"+
                        nameFile +"')]]"))
                .isDisplayed());
            LOG.warn("isQuestionPresent "+text+" - "+nameFile+" "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("isQuestionPresent "+text+" - "+nameFile+" false");
            return false;
        }
    }

    public boolean isSupportPage() {
        try {
            wait.until(ExpectedConditions.urlContains("support"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Тех. поддержка' and @class='active']")));
            LOG.warn("isSupportPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isSupportPage false");
            return false;
        }
    }
}
