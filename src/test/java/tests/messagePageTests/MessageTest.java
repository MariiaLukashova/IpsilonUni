package tests.messagePageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.GroupmatesPage;
import ui.MessagesPage;

@Test(groups = {"Critical path test","Message Test"})
public class MessageTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(MessageTest.class);

    @Test
    public void addDialogueWithStudent() {
        LOG.info("TC_CPT_3.1 addDialogueWithStudent");

        new CommonPage(getDriver()).openMessagesPage();
        new MessagesPage(getDriver()).addDialogue();
        Assert.assertTrue(new GroupmatesPage(getDriver()).writeLetter(),
                "Dialogue with student not created");
    }
    @Test(dependsOnMethods = "addDialogueWithStudent")
    public void sentMessage() {
        LOG.info("TC_CPT_3.3 sentMessage");

        new CommonPage(getDriver()).openMessagesPage();
        new MessagesPage(getDriver()).openDialogue();
        new MessagesPage(getDriver()).sentMessage("HELLO it is auto TEST");
        Assert.assertTrue(new MessagesPage(getDriver()).isMessangePresent("HELLO it is auto TEST"),
                "Message didn't sent");
    }
    @Test(dependsOnMethods = "addDialogueWithStudent"
            , dataProviderClass = DataInfo.class,dataProvider = "textNegative")
    public void sentMessageWithInvalidText(String text) {
        LOG.info("TC_CPT_3.2/TC_CPT_3.2 sentMessageWithInvalidText");

        new CommonPage(getDriver()).openMessagesPage();
        new MessagesPage(getDriver()).openDialogue();
        new MessagesPage(getDriver()).sentMessage(text);
        Assert.assertFalse(new MessagesPage(getDriver()).isMessangePresent(text),
                "Message with invalid text was sent");
    }
}
