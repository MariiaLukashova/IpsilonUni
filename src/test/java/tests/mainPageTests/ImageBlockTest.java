package tests.mainPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;

@Test(groups = {"Critical path test","ImageBlock Test"})
public class ImageBlockTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(ImageBlockTest.class);

    @BeforeMethod
    public void openMainPage() {

        LOG.info("BeforeMethod openMainPage");
        new CommonPage(getDriver()).openMainPage();
    }

    @Test
    public void addImage() {
        LOG.info("TC_CPT_1.1.1 addImage");

        new MainPage(getDriver()).changeAvatar("\\src\\test\\resources\\artifacts\\glaza-kot-usy-koska-klyki-usi-azyk.jpg");
        Assert.assertTrue(new MainPage(getDriver())
                        .isAvatarPresent("glaza-kot-usy-koska-klyki-usi-azyk.jpg"),
                "The avatar on main page isn't " + "glaza-kot-usy-koska-klyki-usi-azyk.jpg");
    }

    @Test(priority = 10,dataProviderClass =DataInfo.class,dataProvider = "fileNegative")
    public void addImageWithInvalidFile(String nameFile) {
        LOG.info("TC_CPT_1.1.2 addImageWithInvalidFile");

        new MainPage(getDriver()).changeAvatar("\\src\\test\\resources\\artifacts\\"+nameFile);
        Assert.assertFalse(new MainPage(getDriver())
                        .isAvatarPresent(nameFile),
                "The avatar on main page isn't " + nameFile);
    }

    @Test(dependsOnMethods = "addImage")
    public void _deleteImage() {
        LOG.info("TC_CPT_1.1.3 deleteImage");

        new MainPage(getDriver()).deleteAvatar();
        Assert.assertTrue(new MainPage(getDriver())
                        .isAvatarPresent("medium_missing"),
                "The avatar on main page didn't delete");
    }
}
