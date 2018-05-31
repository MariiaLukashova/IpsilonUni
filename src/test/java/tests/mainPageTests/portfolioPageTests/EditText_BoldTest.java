package tests.mainPageTests.portfolioPageTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;
import ui.PortfolioPage;

@Test(groups = {"Critical path test","Edit Text Bold Test"})
public class EditText_BoldTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(EditText_BoldTest.class);

    @BeforeMethod
    public void openPortfolio() {
        LOG.info("BeforeMethod openPortfolio");
        new CommonPage(getDriver()).openMainPage();
        new MainPage(getDriver()).openPortfolio();
    }
    @AfterMethod
    public void safeEditingPortfolio() {
        LOG.info("TC_CPT_7.1.4 AfterMethod safeEditingPortfolio");

        new PortfolioPage(getDriver()).safeEditing();
        Assert.assertTrue(new PortfolioPage(getDriver()).isTextPresent("Текст"),
                "Edit didn't safe on page portfolio");
    }

    @Test
    public void makeBoldTextOnCommonPortfolio() {
        LOG.info("TC_CPT_7.1.1 makeBoldTextOnCommonPortfolio");

        new PortfolioPage(getDriver()).openCommonPortfolio();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextBolt(),
                "the text isn't bold on page of common portfolio");
    }
    @Test
    public void makeBoldTextOnCourseWorksPortfolio() {
        LOG.info("TC_CPT_7.1.1 makeBoldTextOnCourseWorksPortfolio");

        new PortfolioPage(getDriver()).openCourseWorks();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextBolt(),
                "the text isn't bold on page of CourseWorks portfolio");
    }
    @Test
    public void makeBoldTextOnPractiksPortfolio() {
        LOG.info("TC_CPT_7.1.1 makeBoldTextOnPractiksPortfolio");

        new PortfolioPage(getDriver()).openPractiks();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextBolt(),
                "the text isn't bold on page of Practiks portfolio");
    }
    @Test
    public void makeBoldTextOnIndividualAchievementsPortfolio() {
        LOG.info("TC_CPT_7.1.1 makeBoldTextOnIndividualAchievementsPortfolio");

        new PortfolioPage(getDriver()).openIndividualAchievements();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextBolt(),
                "the text isn't bold on page of IndividualAchievements portfolio");
    }
    @Test
    public void makeBoldTextOnOtherInfoPortfolio() {
        LOG.info("TC_CPT_7.1.1 makeBoldTextOnOtherInfoPortfolio");

        new PortfolioPage(getDriver()).openOther();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextBolt(),
                "the text isn't bold on page of OtherInfo portfolio");
    }
}
