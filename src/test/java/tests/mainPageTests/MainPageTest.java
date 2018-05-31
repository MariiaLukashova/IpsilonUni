package tests.mainPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.ChangeEmailPage;
import ui.CommonPage;
import ui.MainPage;

@Test(groups = "Main Page Test")
public class MainPageTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(MainPageTest.class);

    @BeforeMethod
    public void openMainPage() {
        LOG.info("BeforeMethod openMainPage");
        new CommonPage(getDriver()).openMainPage();
    }

    @Test(groups = "Smoke test")
    public void isInfoBlock() {
        LOG.info("TC_2.2 isInfoBlock");

        Assert.assertTrue(new MainPage(getDriver()).isInfoBlockPresent(),
                "On main page isn't info block");
    }

    @Test(groups = "Critical path test")
    public void isNewsBlock() {
        LOG.info("TC_CPT_1.2. isNewsBlock");

        Assert.assertTrue(new MainPage(getDriver()).isNewsBlockPresent(),
                "On main page isn't news block");
    }

    @Test(groups = "Critical path test",
            dataProviderClass =DataInfo.class,dataProvider = "city")
    public void changeCity(String city,String value) {
        LOG.info("TC_CPT_1.5. changeCity");

        new MainPage(getDriver()).changeCity(value);
        Assert.assertTrue(new MainPage(getDriver()).isCityPresent(city),
                "The city didn't change");
    }

    @Test(groups = "Critical path test",enabled = false)
    public void changeEmail() {
        LOG.info("TC_CPT_1.6.1. changeEmail");

        new MainPage(getDriver()).openChangeEmailPage();
        new ChangeEmailPage(getDriver()).setEmail("mari-lukashowa@yandex.ru");
        new ChangeEmailPage(getDriver()).setCurrentPassword("password");
        new ChangeEmailPage(getDriver()).clickBtnSafe();
        Assert.assertTrue(new MainPage(getDriver()).isEmailPresent("mari-lukashowa@yandex.ru"),
                "The email didn't change");
    }

    @Test(groups = "Critical path test",
            dataProviderClass =DataInfo.class,dataProvider = "emailNegativeAndCurrentPassword")
    public void changeEmailWithInvalidText(String email, String curPassword) {
        LOG.info("TC_CPT_1.6.2./TC_CPT_1.6.3./TC_CPT_1.6.4. changeEmailWithInvalidText");

        new MainPage(getDriver()).openChangeEmailPage();
        new ChangeEmailPage(getDriver()).setEmail(email);
        new ChangeEmailPage(getDriver()).setCurrentPassword(curPassword);
        new ChangeEmailPage(getDriver()).clickBtnSafe();
        Assert.assertTrue(new ChangeEmailPage(getDriver()).isErrorPresent(),
                "Message error isn't displayed");
        new ChangeEmailPage(getDriver()).clickBtnBack();
    }

}
