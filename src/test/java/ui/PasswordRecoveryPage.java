package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PasswordRecoveryPage extends Page {
    private static final Logger LOG = Logger.getLogger(PasswordRecoveryPage.class);

    @FindBy(id = "inputEmail")
    private WebElement inputLogin;
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSubmit;
    @FindBy(xpath = "//a[@class='link-back']")
    private WebElement linkBack;
    @FindBy(id = "error_explanation")
    private WebElement messageError;
    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    public Page setEmail(String email){
        LOG.info("setEmail "+email);
        inputLogin.clear();
        actions.sendKeys(inputLogin, email)
                .build().perform();
        return this;
    }
    public Page clickBtnSubmit(){
        LOG.info("clickBtnSubmit");
        btnSubmit.click();
        return this;
    }
    public Page clickBtnBack(){
        LOG.info("clickBtnBack");
        linkBack.click();
        return this;
    }
    public boolean isErrorPresent() {
        try {
            LOG.warn("isErrorPresent "+messageError.isDisplayed());
            return messageError.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isErrorPresent false");
            return false;
        }
    }
    public boolean isPasswordRecoveryPage() {
        try {
            wait.until(ExpectedConditions.urlContains("password"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[text()='Смена пароля']")));
            LOG.warn("isPasswordRecoveryPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isPasswordRecoveryPage false");
            return false;
        }
    }
}
