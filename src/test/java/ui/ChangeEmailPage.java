package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChangeEmailPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(ChangeEmailPage.class);

    @FindBy(id = "inputEmail")
    private WebElement inputNewEmail;
    @FindBy(id = "user_current_password")
    private WebElement inputCurrentPassword;
    @FindBy(xpath = "//input[@value='Сохранить']")
    private WebElement btnSafe;
    @FindBy(xpath = "//a[@class='link-back']")
    private WebElement btnBack;
    @FindBy(id = "error_explanation")
    private WebElement divError;

    public ChangeEmailPage(WebDriver driver) {
        super(driver);
    }

    public Page setEmail(String email) {
        inputNewEmail.clear();
        inputNewEmail.sendKeys(email);
        LOG.info("setEmail "+email);
        return this;
    }

    public Page setCurrentPassword(String currentPassword) {
        inputCurrentPassword.clear();
        inputCurrentPassword.sendKeys(currentPassword);
        LOG.info("setCurrentPassword "+currentPassword);
        return this;
    }

    public Page clickBtnSafe() {
        LOG.info("clickBtnSafe");
        btnSafe.click();
        return new MainPage(driver);                        //!!!!
    }

    public Page clickBtnBack() {
        LOG.info("clickBtnBack");
        btnBack.click();
        return new MainPage(driver);
    }

    public boolean isErrorPresent() {
        try {
            LOG.warn("isErrorPresent "+divError.isDisplayed());
            return divError.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isErrorPresent false");
            return false;
        }
    }

    public boolean isChangeEmailPage() {
        try {
            wait.until(ExpectedConditions.urlContains("edit"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[text()='Смена E-mail']")));
            LOG.warn("isChangeEmailPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isChangeEmailPage false");
            return false;
        }
    }
}
