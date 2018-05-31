package tests;

import businessobject.User;
import exceptions.UsernameOrPasswordIsIncorrect;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.LoginPage;
import ui.Page;

@Test(groups = {"Critical path test","Login Test"})
public class LoginTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(LoginTest.class);

    @BeforeClass
    @Override
    public void x_Login() {
    }
    @AfterClass
    @Override
    public void authorizationOut() {}
    @Test(expectedExceptions = UsernameOrPasswordIsIncorrect.class)
    public void loginTrue_and_PasswordFalse() {
        LOG.info("TC_CPT_2.1.1 loginTrue_and_PasswordFalse");
        new LoginPage(page.getDriver()).login(new User("lukashovama@sgu.ru", "111111"));
    }

    @Test(expectedExceptions = UsernameOrPasswordIsIncorrect.class)
    public void loginFalse_and_PasswordTrue() {
        LOG.info("TC_CPT_2.1.2 loginFalse_and_PasswordTrue");
        new LoginPage(page.getDriver()).login(new User("masha@yandex.ru", "password"));
    }

    @Test(expectedExceptions = UsernameOrPasswordIsIncorrect.class)
    public void loginFalse_and_PasswordFalse() {
        LOG.info("TC_CPT_2.1.3 loginFalse_and_PasswordFalse");
        new LoginPage(page.getDriver()).login(new User("masha@yandex.ru", "1.11217"));
    }
}
