package tests.mainPageTests.portfolioPageTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;
import ui.PortfolioPage;

@Test(groups = {"Critical path test","Edit Text Italic Test"})
public class EditText_ItalicTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(EditText_ItalicTest.class);

    @BeforeMethod
    public void openPortfolio() {
        LOG.info("BeforeMethod openPortfolio");
        new CommonPage(getDriver()).openMainPage();
        new MainPage(getDriver()).openPortfolio();
    }


    @Test
    public void makeItalicTextOnCommonPortfolio() {
        LOG.info("TC_CPT_7.1.2 makeItalicTextOnCommonPortfolio");

        new PortfolioPage(getDriver()).openCommonPortfolio();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextItalic(),
                "the text isn't italic on page of common portfolio");
    }
    @Test
    public void makeItalicTextOnCourseWorksPortfolio() {
        LOG.info("TC_CPT_7.1.2 makeItalicTextOnCourseWorksPortfolio");

        new PortfolioPage(getDriver()).openCourseWorks();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextItalic(),
                "the text isn't italic on page of CourseWorks portfolio");
    }
    @Test
    public void makeItalicTextOnPractiksPortfolio() {
        LOG.info("TC_CPT_7.1.2 makeItalicTextOnPractiksPortfolio");

        new PortfolioPage(getDriver()).openPractiks();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextItalic(),
                "the text isn't italic on page of Practiks portfolio");
    }
    @Test
    public void makeItalicTextOnIndividualAchievementsPortfolio() {
        LOG.info("TC_CPT_7.1.2 makeItalicTextOnIndividualAchievementsPortfolio");

        new PortfolioPage(getDriver()).openIndividualAchievements();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextBolt(),
                "the text isn't italic on page of IndividualAchievements portfolio");
    }
    @Test
    public void makeItalicTextOnOtherInfoPortfolio() {
        LOG.info("TC_CPT_7.1.2 makeItalicTextOnOtherInfoPortfolio");

        new PortfolioPage(getDriver()).openOther();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).enterText("Текст");
        Assert.assertTrue(new PortfolioPage(getDriver()).setTextItalic(),
                "the text isn't italic on page of OtherInfo portfolio");
    }
}
