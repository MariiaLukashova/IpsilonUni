package tests.mainPageTests.portfolioPageTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;
import ui.PortfolioPage;

@Test(groups = {"Critical path test","Edit Text Underline Test"})
public class EditText_UnderlineTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(EditText_UnderlineTest.class);

    @BeforeMethod
    public void openPortfolio() {
        LOG.info("BeforeMethod openPortfolio");
        new CommonPage(getDriver()).openMainPage();
        new MainPage(getDriver()).openPortfolio();
    }


    @Test
    public void makeUnderlineTextOnCommonPortfolio() {
        LOG.info("TC_CPT_7.1.3 makeUnderlineTextOnCommonPortfolio");

        new PortfolioPage(getDriver()).openCommonPortfolio();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextUnderline(),
                "the text isn't underline on page of common portfolio");
    }
    @Test
    public void makeUnderlineTextOnCourseWorksPortfolio() {
        LOG.info("TC_CPT_7.1.3 makeUnderlineTextOnCourseWorksPortfolio");

        new PortfolioPage(getDriver()).openCourseWorks();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextUnderline(),
                "the text isn't underline on page of CourseWorks portfolio");
    }
    @Test
    public void makeUnderlineItalicTextOnPractiksPortfolio() {
        LOG.info("TC_CPT_7.1.3 makeUnderlineTextOnPractiksPortfolio");

        new PortfolioPage(getDriver()).openPractiks();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextUnderline(),
                "the text isn't underline on page of Practiks portfolio");
    }
    @Test
    public void makeUnderlineTextOnIndividualAchievementsPortfolio() {
        LOG.info("TC_CPT_7.1.3 makeUnderlineTextOnIndividualAchievementsPortfolio");

        new PortfolioPage(getDriver()).openIndividualAchievements();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextUnderline(),
                "the text isn't underline on page of IndividualAchievements portfolio");
    }
    @Test
    public void makeUnderlineTextOnOtherInfoPortfolio() {
        LOG.info("TC_CPT_7.1.3 makeUnderlineTextOnOtherInfoPortfolio");

        new PortfolioPage(getDriver()).openOther();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextUnderline(),
                "the text isn't underline on page of OtherInfo portfolio");
    }
}
