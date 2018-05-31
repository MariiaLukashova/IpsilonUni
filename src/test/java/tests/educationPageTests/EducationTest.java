package tests.educationPageTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.EducationPage;

@Test(groups = {"Education Test", "Smoke test"})
public class EducationTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(EducationTest.class);

    @Test
    public void isInfoSubject() {
        LOG.info("TC_4.1 isInfoSubject");
        new CommonPage(getDriver()).openEducationPage();
        new EducationPage(getDriver()).openSemester(2);
        new EducationPage(getDriver()).openInfoSubject();
        Assert.assertTrue(new EducationPage(getDriver()).isInfoBlockSubjectPresent(),
                "On Education page isn't info block of subject");
    }

    @Test(dependsOnMethods = "isInfoSubject")
    public void openDialogueWithTeacher() {
        LOG.info("TC_4.2 openDialogueWithTeacher");
        Assert.assertTrue(new EducationPage(getDriver()).writeMessange(),
                "It could not write a letter to teacher");
    }

    @Test(priority = 2)
    public void openSubject() {
        LOG.info("TC_4.3 openSubject");
        new CommonPage(getDriver()).openEducationPage();
        new EducationPage(getDriver()).openSemester(8);
        Assert.assertTrue(new EducationPage(getDriver()).openSubject(),
                "It could not open a page subject");
    }

    @Test(dependsOnMethods = "openSubject")
    public void isTablePresent() {
        LOG.info("TC_5.1 isTablePresent");
        Assert.assertTrue(new EducationPage(getDriver()).isTablePresent(),
                "On page of Subject isn't block of 'Таблица видов учебной деятельности'");
    }

    @Test(dependsOnMethods = "openSubject")
    public void isStatisticPresent() {
        LOG.info("TC_5.2 isStatisticPresent");
        Assert.assertTrue(new EducationPage(getDriver()).isStatisticPresent(),
                "On page of Subject isn't block of 'Статистика отправки работ'");
    }

    @Test(dependsOnMethods = "openSubject")
    public void isStructurePresent() {
        LOG.info("TC_5.3 isStructurePresent");
        Assert.assertTrue(new EducationPage(getDriver()).isStructurePresent(),
                "On page of Subject isn't block of 'Структура курса'");
    }

    @Test(dependsOnMethods = "openSubject")
    public void openDocument() {
        LOG.info("TC_5.4 openDocument");
        Assert.assertTrue(new EducationPage(getDriver()).openDocument(),
                "It could not open a page with document");
    }

}
