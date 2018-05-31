package tests.mainPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import tests.mainPageTests.NewsTest;
import ui.CommonPage;
import ui.MainPage;

@Test(groups = {"Critical path test", "Note Test"})
public class NoteTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(NewsTest.class);

    @BeforeMethod
    public void openMainPage() {
        LOG.info("BeforeMethod openMainPage");
        new CommonPage(getDriver()).openMainPage();
    }

    @Test
    public void addNote() {
        LOG.info("TC_CPT_1.4.2 addNote");

        int countNote = new MainPage(getDriver()).countNote();
        new MainPage(getDriver()).addNote("HELLO it is auto TEST");
        Assert.assertTrue(new MainPage(getDriver()).isNotePresent("HELLO it is auto TEST") &&
                        new MainPage(getDriver()).countNote() == countNote + 1,
                "The note on main page didn't add in list");
    }

    @Test(dataProviderClass = DataInfo.class, dataProvider = "textNegative")
    public void addNoteWithInvalidText(String text) {
        LOG.info("TC_CPT_1.4.1/TC_CPT_1.4.3 addNoteWithInvalidText");

        int countNote = new MainPage(getDriver()).countNote();
        new MainPage(getDriver()).addNote(text);
        Assert.assertTrue(!new MainPage(getDriver()).isNotePresent(text) &&
                        new MainPage(getDriver()).countNote() == countNote,
                "The note with invalid text added in list");
    }

    @Test(dependsOnMethods = "addNote")
    public void deleteNote() {
        LOG.info("TC_CPT_1.4.4 deleteNote");

        int countNote = new MainPage(getDriver()).countNote();
        new MainPage(getDriver()).deleteNote("HELLO it is auto TEST");
        Assert.assertTrue(!new MainPage(getDriver()).isNotePresent("HELLO it is auto TEST") &&
                        new MainPage(getDriver()).countNote() == countNote - 1,
                "The note on main page didn't delete from list");
    }
}
