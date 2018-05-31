package tests.mainPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;
import ui.NewsPage;

@Test(groups = {"Critical path test","News Test"})
public class NewsTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(NewsTest.class);

    @BeforeMethod
    public void openMainPage() {
        LOG.info("BeforeMethod openMainPage");
        new CommonPage(getDriver()).openMainPage();
    }
    @Test
    public void commentNews() {
        LOG.info("TC_CPT_1.3.2 commentNews");

        new MainPage(getDriver()).openNews();
        int countMessage = new NewsPage(getDriver()).countItemMessage();
        new NewsPage(getDriver()).sentMessange("HELLO it is auto TEST");
        Assert.assertTrue(new NewsPage(getDriver()).isMessangePresent("HELLO it is auto TEST") &&
                        new NewsPage(getDriver()).countItemMessage() == countMessage + 1,
                "Message didn't sent");
    }

    @Test(dataProviderClass = DataInfo.class,dataProvider = "textNegative")
    public void commentNewsWithInvalidMessage(String message) {
        LOG.info("TC_CPT_1.3.1/TC_CPT_1.3.3 commentNewsWithInvalidMessage");

        new MainPage(getDriver()).openNews();
        int countMessage = new NewsPage(getDriver()).countItemMessage();
        new NewsPage(getDriver()).sentMessange(message);
        Assert.assertTrue(!new NewsPage(getDriver()).isMessangePresent(message) &&
                        new NewsPage(getDriver()).countItemMessage() == countMessage,
                "Message with invalid text sent! Karl!!");
    }
}
