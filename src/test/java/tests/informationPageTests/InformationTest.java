package tests.informationPageTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.GroupmatesPage;
import ui.InformationPage;

@Test(groups = {"Critical path test","Information Test"})
public class InformationTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(InformationTest.class);


    @Test
    public void isContactsAndDocumentsBlock() {
        LOG.info("TC_CPT_4.1 isContactsAndDocumentsBlock");

        new CommonPage(getDriver()).openInformationPage();
        Assert.assertTrue(new InformationPage(getDriver()).isContactsPresent() &&
                new InformationPage(getDriver()).isDocumentsPresent(),
                "On page Information aren't Contacts and Documents blocks");
    }
    @Test(dependsOnMethods = "isContactsAndDocumentsBlock")
    public void openDocument() {
        LOG.info("TC_CPT_4.2 openDocument");

        new CommonPage(getDriver()).openInformationPage();
        Assert.assertTrue(new InformationPage(getDriver()).openDocument(),
                "It could not open a page with document");
    }
    @Test
    public void isGroupmatesListBlock() {
        LOG.info("TC_CPT_5.1 isGroupmatesListBlock");

        new CommonPage(getDriver()).openGroupmatesPage();
        Assert.assertTrue(new GroupmatesPage(getDriver()).isGroupmatesListPresent(),
                "On page Groupmates is't list of groupmates block");
    }

}
