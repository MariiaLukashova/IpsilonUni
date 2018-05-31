package tests.mainPageTests.portfolioPageTests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;
import ui.PortfolioPage;

@Test(groups = "Portfolio Test")
public class PortfolioTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(PortfolioTest.class);

    @BeforeMethod
    public void openPortfolio() {
        LOG.info("BeforeMethod openPortfolio");
        new CommonPage(getDriver()).openMainPage();
        new MainPage(getDriver()).openPortfolio();
    }

    @Test(groups = "Smoke test")
    public void openAllPortfolioInViewingMode() {
        LOG.info("TC_7.1 openAllPortfolioInViewingMode");

        new PortfolioPage(getDriver()).openAllPortfolio();
        Assert.assertTrue(new PortfolioPage(getDriver()).isViewingMode(),
                "Page with all portfolio isn't in mode of a viewing");
    }

    @Test(groups = "Smoke test")
    public void openCommonPortfolioInViewingMode() {
        LOG.info("TC_7.2 openCommonPortfolioInViewingMode");

        new PortfolioPage(getDriver()).openCommonPortfolio();
        Assert.assertTrue(new PortfolioPage(getDriver()).isViewingMode(),
                "Portfolio page with common info isn't in mode of a viewing");
    }

    @Test(groups = "Smoke test")
    public void openCourseWorksPortfolioInViewingMode() {
        LOG.info("TC_7.3 openCourseWorksPortfolioInViewingMode");
        new PortfolioPage(getDriver()).openCourseWorks();
        Assert.assertTrue(new PortfolioPage(getDriver()).isViewingMode(),
                "Portfolio page with courseworks info isn't in mode of a viewing");
    }

    @Test(groups = "Smoke test")
    public void openPractiksPortfolioInViewingMode() {
        LOG.info("TC_7.4 openPractiksPortfolioInViewingMode");
        new PortfolioPage(getDriver()).openPractiks();
        Assert.assertTrue(new PortfolioPage(getDriver()).isViewingMode(),
                "Portfolio page with practiks info isn't in mode of a viewing");
    }

    @Test(groups = "Smoke test")
    public void openIndividualAchievementsPortfolioInViewingMode() {
        LOG.info("TC_7.5 openIndividualAchievementsPortfolioInViewingMode");
        new PortfolioPage(getDriver()).openIndividualAchievements();
        Assert.assertTrue(new PortfolioPage(getDriver()).isViewingMode(),
                "Portfolio page with individual achievements info isn't in mode of a viewing");
    }

    @Test(groups = "Smoke test")
    public void openOtherInfoPortfolioInViewingMode() {
        LOG.info("TC_7.6 openOtherInfoPortfolioInViewingMode");
        new PortfolioPage(getDriver()).openOther();
        Assert.assertTrue(new PortfolioPage(getDriver()).isViewingMode(),
                "Portfolio page with other info isn't in mode of a viewing");
    }

    @Test(groups = "Critical path test")
    public void downloadPortfolio() {
        LOG.info("TC_CPT_7.4 downloadPortfolio");

        new PortfolioPage(getDriver()).openAllPortfolio();
        Assert.assertTrue(new PortfolioPage(getDriver()).downloadPortfolio(),
                "Portfolio didn't open in pdf format");
    }
}
