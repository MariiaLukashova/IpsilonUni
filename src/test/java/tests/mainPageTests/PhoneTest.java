package tests.mainPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;

@Test(groups = {"Critical path test","Phone Test"})
public class PhoneTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(PhoneTest.class);

    @BeforeMethod
    public void openMainPage() {
        LOG.info("BeforeMethod openMainPage");
        new CommonPage(getDriver()).openMainPage();
    }


    @Test(dataProviderClass = DataInfo.class,dataProvider = "phones")
    public void changePhone(String phone) {
        LOG.info("TC_CPT_1.8.1 changePhone");

        new MainPage(getDriver()).changePhone(phone);
        Assert.assertTrue(new MainPage(getDriver()).isPhonePresent(phone),
                "The phone didn't change");
    }
    @Test
    public void changePhoneWithEmptyField() {
        LOG.info("TC_CPT_1.8.1 changePhone");

        new MainPage(getDriver()).changePhone("");
        Assert.assertTrue(new MainPage(getDriver()).isPhonePresent("Пусто"),
                "The phone didn't change");
    }
    @Test(dataProviderClass =DataInfo.class,dataProvider = "phonesNegative")
    public void changePhoneWithInvalidText(String phone) {
        LOG.info("TC_CPT_1.8.2/TC_CPT_1.8.3 changePhoneWithInvalidText");

        new MainPage(getDriver()).changePhone(phone);
        Assert.assertFalse(new MainPage(getDriver()).isPhonePresent(phone),
                "Invalid text safe as phone");
    }
}
