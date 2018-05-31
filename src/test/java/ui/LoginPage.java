package ui;

import businessobject.User;
import exceptions.NoMessage_UsernameOrPasswordIsIncorrect;
import exceptions.UsernameOrPasswordIsIncorrect;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    @FindBy(id = "inputEmail")
    private WebElement inputLogin;
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;
    @FindBy(id = "sign_in")
    private WebElement btnSubmit;
    @FindBy(xpath = "//div[@class='f-options']/a")
    private WebElement linkForgotPassword;
    @FindBy(xpath = "//div[@class='alert-danger']")
    private WebElement messageError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public Page login(User user) {
        inputLogin.clear();
        inputPassword.clear();
        actions.sendKeys(inputLogin, user.getLogin())
                .sendKeys(inputPassword, user.getPassword())
                .click(btnSubmit)
                .build().perform();

        if (new MainPage(this.driver).isMainPage()) {
            LOG.warn("x_Login: x_Login-" + user.getLogin()+" , password- " + user.getPassword());
            return new MainPage(this.driver);
        } else if (messageError.getText() != "") {
            LOG.warn("Username or password is incorrect: x_Login-" + user.getLogin()
                    + ", password- " + user.getPassword());
            throw new UsernameOrPasswordIsIncorrect(user.getLogin(), user.getPassword());
        } else {
            LOG.warn("No message 'Username or password is incorrect': x_Login-" + user.getLogin()
                    + ", password-" + user.getPassword());
            throw new NoMessage_UsernameOrPasswordIsIncorrect(user.getLogin(), user.getPassword());
        }
    }
    public Page openRecoveryPassword(){
        LOG.info("openRecoveryPassword");
        linkForgotPassword.click();
        wait.until(webDriver -> new PasswordRecoveryPage(webDriver).isPasswordRecoveryPage());
        return new PasswordRecoveryPage(this.driver);
    }

    public boolean isLoginPage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(btnSubmit));
            LOG.warn("isLoginPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isLoginPage false");
            return false;
        }
    }
}
