package tests.mainPageTests.portfolioPageTests;

import data.DataInfo;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;
import ui.CommonPage;
import ui.MainPage;
import ui.PortfolioPage;

@Test(groups = {"Critical path test","Add File In Portfolio Test"})
public class AddFileInPortfolioTest extends BaseTest {
    private static final Logger LOG = Logger.getLogger(AddFileInPortfolioTest.class);

    @BeforeMethod
    public void openPortfolio() {
        LOG.info("BeforeMethod openPortfolio");
        new CommonPage(getDriver()).openMainPage();
        new MainPage(getDriver()).openPortfolio();
    }
    @Test
    public void addFileAndSafe() {
        LOG.info("TC_CPT_7.2 addFileAndSafe");

        new PortfolioPage(getDriver()).openCommonPortfolio();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).addFile("\\src\\test\\resources\\artifacts\\glaza-kot-usy-koska-klyki-usi-azyk.jpg");
        new PortfolioPage(getDriver()).safeEditing();
        Assert.assertTrue(new PortfolioPage(getDriver()).isFilePresent("glaza-kot-usy-koska-klyki-usi-azyk.jpg"),
                "File didn't safe on page of Common portfolio");
    }
    @Test(dataProviderClass = DataInfo.class,dataProvider = "fileNegative")
    public void addFileAndSafeWithInvalidFile(String nameFile) {
        LOG.info("TC_CPT_7.3 addFileAndSafeWithInvalidFile");

        new PortfolioPage(getDriver()).openCommonPortfolio();
        new PortfolioPage(getDriver()).openEditMode();
        new PortfolioPage(getDriver()).addFile("\\src\\test\\resources\\artifacts\\"+nameFile);
        new PortfolioPage(getDriver()).safeEditing();
        Assert.assertTrue(!new PortfolioPage(getDriver()).isFilePresent(nameFile) &&
                new PortfolioPage(getDriver()).isPortfolioPage(),
                "Invalid file was safe on page of Common portfolio");
    }
}
