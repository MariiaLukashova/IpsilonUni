package tests.mainPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;

@Test(groups = {"Critical path test","Birthday Test"})
public class BirthdayTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(BirthdayTest.class);

    @BeforeMethod
    public void openMainPage() {
        LOG.info("BeforeMethod openMainPage");
        new CommonPage(getDriver()).openMainPage();
    }

    @Test(dataProviderClass = DataInfo.class, dataProvider = "birthdays")
    public void changeBirthday(String date) {
        LOG.info("TC_CPT_1.7.1 changeBirthday");

        new MainPage(getDriver()).changeBirthday(date);
        Assert.assertTrue(new MainPage(getDriver()).isBirthdayPresent(date),
                "The birthdays didn't change");
    }

    @Test
    public void changeBirthdayWithEmptyField() {
        LOG.info("TC_CPT_1.7.1 changeBirthday");

        new MainPage(getDriver()).changeBirthday("");
        Assert.assertTrue(new MainPage(getDriver()).isBirthdayPresent("Пусто"),
                "The birthdays didn't change");
    }

    @Test(dataProviderClass = DataInfo.class, dataProvider = "birthdaysNegative")
    public void changeBirthdayWithInvalidText(String date) {
        LOG.info("TC_CPT_1.7.2/TC_CPT_1.7.3 changeBirthdayWithInvalidText");

        new MainPage(getDriver()).changeBirthday(date);
        Assert.assertFalse(new MainPage(getDriver()).isBirthdayPresent(date),
                "Invalid text safe as birthdays!!");
    }
}
