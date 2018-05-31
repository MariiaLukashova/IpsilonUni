package tests.supportPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.SupportPage;

@Test(groups = "Support Test")
public class SupportTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(SupportTest.class);

    @Test(groups = "Smoke test")
    public void askQuestion() {
        LOG.info("TC_6.1 askQuestion");

        new CommonPage(getDriver()).openSupportPage();
        new SupportPage(getDriver()).sentQuestion("HELLO it is auto TEST");
        Assert.assertTrue(new SupportPage(getDriver()).isQuestionPresent("HELLO it is auto TEST"),
                "The question isn't exist");
    }

    @Test(groups = "Critical path test")
    public void askQuestionWithFile() {
        LOG.info("TC_6.3 askQuestionWithFile");

        new CommonPage(getDriver()).openSupportPage();
        new SupportPage(getDriver())
                .sentQuestion("TEST", "\\src\\test\\resources\\artifacts\\glaza-kot-usy-koska-klyki-usi-azyk.jpg");
        Assert.assertTrue(new SupportPage(getDriver())
                        .isQuestionPresent("TEST", "glaza-kot-usy-koska-klyki-usi-azyk.jpg"),
                "The question isn't exist");
    }

    @Test(groups = "Critical path test",
            dataProviderClass = DataInfo.class, dataProvider = "questionsNegative")
    public void askQuestionWithInvalidText(String text, String nameFile) {
        LOG.info("TC_CPT_6.1/6.2/6.4 askQuestionWithInvalidText");

        new CommonPage(getDriver()).openSupportPage();
        new SupportPage(getDriver()).sentQuestion(text, "\\src\\test\\resources\\artifacts\\"+nameFile);
        Assert.assertFalse(new SupportPage(getDriver()).isQuestionPresent(text, nameFile),
                "The question with invalid data is exist");
    }

}
