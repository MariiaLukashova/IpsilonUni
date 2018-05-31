package tests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import ui.LoginPage;
import ui.Page;
import ui.PasswordRecoveryPage;

@Test
public class PasswordRecoveryTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(PasswordRecoveryTest.class);

    @BeforeClass
    @Override
    public void x_Login() {
    }
    @AfterClass
    @Override
    public void authorizationOut() {}
    @BeforeMethod
    public void openRecoveryPassword() {
        LOG.info("BeforeMethod openRecoveryPassword");

        new LoginPage(getDriver()).openRecoveryPassword();
    }

    @Test(groups = "Smoke test")
    public void recoveryPassword() {
        LOG.info("TC_3.2. recoveryPassword");

        new PasswordRecoveryPage(getDriver()).setEmail("mari-lukashowa@yandex.ru");
        new PasswordRecoveryPage(getDriver()).clickBtnSubmit();
        Assert.assertTrue(new LoginPage(getDriver()).isLoginPage(), "The password didn't recovery");
    }

    @Test(groups = "Critical path test",
            dataProviderClass = DataInfo.class, dataProvider = "emailNegative")
    public void recoveryPassword_withInvalidEmail(String email) {
        LOG.info("TC_CPT_2.2.1/TC_CPT_2.2.2 recoveryPassword_withInvalidEmail");

        new PasswordRecoveryPage(getDriver()).setEmail(email);
        new PasswordRecoveryPage(getDriver()).clickBtnSubmit();
        Assert.assertTrue(new PasswordRecoveryPage(getDriver()).isErrorPresent(),
                "The message error isn't displayed");
    }

    @AfterMethod
    public void openLoginPage() {
        LOG.info("AfterMethod openLoginPage");
        new PasswordRecoveryPage(getDriver()).clickBtnBack();
    }

}
