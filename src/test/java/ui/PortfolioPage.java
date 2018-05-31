package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class PortfolioPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(PortfolioPage.class);

    @FindBy(xpath = "//a[text()='Просмотр портфолио']")
    private WebElement linkAllPortfolio;
    @FindBy(xpath = "//a[text()='Общие сведения']")
    private WebElement linkCommonPortfolio;
    @FindBy(xpath = "//a[text()='Список курсовых работ студента (в течение периода обучения)']")
    private WebElement linkCoursWorks;
    @FindBy(xpath = "//a[text()='Список практик студента (в течение периода обучения)']")
    private WebElement linkPractiks;
    @FindBy(xpath = "//a[text()='Индивидуальные достижения студента']")
    private WebElement linkIndividualAchievements;
    @FindBy(xpath = "//a[text()='Стажировки, включенное обучение в рамках сетевой программы, другие формы академической мобильности']")
    private WebElement linkOther;

    @FindBy(xpath = "//div[@class='b-widget-head-action']/a")
    private WebElement linkDowloadPortfolio;
    @FindBy(xpath = "//a[./i[@class='icon-pencil']]")
    private WebElement btnEdit;
    @FindBy(xpath = "//span[contains(@class,'cke_button__bold_icon')]")
    private WebElement btnBolt;
    @FindBy(xpath = "//span[contains(@class,'cke_button__italic_icon')]")
    private WebElement btnItalic;
    @FindBy(xpath = "//span[contains(@class,'cke_button__underline_icon')]")
    private WebElement btnUnderline;
    @FindBy(xpath = "//iframe")
    private WebElement iframe;
    @FindBy(id = "add_file")
    private WebElement addFile;
    @FindBy(xpath = "//div[@class='f-group-action']/input[@name='commit']")
    private WebElement btnSafe;
    @FindBy(xpath = "//div[@class='f-group']/input[@type='file']")
    private WebElement btnChooseFile;

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    public Page openAllPortfolio() {
        LOG.info("openAllPortfolio");
        linkAllPortfolio.click();
        wait.until(ExpectedConditions.attributeToBe(linkAllPortfolio
                , "class", "active"));
        return this;
    }

    public Page openCommonPortfolio() {
        LOG.info("openCommonPortfolio");
        linkCommonPortfolio.click();
        wait.until(ExpectedConditions.attributeToBe(linkCommonPortfolio
                , "class", "active"));
        return this;
    }

    public Page openCourseWorks() {
        LOG.info("openCoursWorks");
        linkCoursWorks.click();
        wait.until(ExpectedConditions.attributeToBe(linkCoursWorks
                , "class", "active"));
        return this;
    }

    public Page openPractiks() {
        LOG.info("openPractiks");
        linkPractiks.click();
        wait.until(ExpectedConditions.attributeToBe(linkPractiks
                , "class", "active"));
        return this;
    }

    public Page openIndividualAchievements() {
        LOG.info("openIndividualAchievements");
        linkIndividualAchievements.click();
        wait.until(ExpectedConditions.attributeToBe(linkIndividualAchievements
                , "class", "active"));
        return this;
    }

    public Page openOther() {
        LOG.info("openOther");
        linkOther.click();
        wait.until(ExpectedConditions.attributeToBe(linkOther
                , "class", "active"));
        return this;
    }

    public boolean isViewingMode() {
        try {
            LOG.warn("openSubject "+btnEdit.isDisplayed());
            return btnEdit.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("openSubject false");
            return false;
        }
    }

    public Page openEditMode() {
        LOG.info("openEditMode");
        btnEdit.click();
        wait.until(ExpectedConditions.visibilityOf(btnBolt));
        return this;
    }

    public Page addFile(String pathFile) {
        LOG.info("addFile "+pathFile);
        addFile.click();
        wait.until(ExpectedConditions.elementToBeClickable(btnChooseFile));
        btnChooseFile.sendKeys(new File("").getAbsolutePath()+pathFile);
        return this;
    }

    public Page safeEditing() {
        LOG.info("safeEditing");
        btnSafe.click();
        waitIsNotElementPresent(btnSafe);
        return this;
    }
    public boolean isTextPresent(String text) {
        try {
            boolean isDisplayed=driver.findElement(By.xpath("//p/*[text()='" + text + "']"))
                    .isDisplayed();
            LOG.warn("isTextPresent "+text+" "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("isTextPresent "+text+" false");
            return false;
        }
    }
    public boolean isFilePresent(String fileName) {
        try {
            boolean isDisplayed=driver.findElement(By.xpath("//a[text()='" + fileName + "']"))
                .isDisplayed();
            LOG.warn("isFilePresent "+fileName+" "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("isFilePresent "+fileName+" false");
            return false;
        }
    }

    public Page enterText(String text) {
        driver.switchTo().frame(iframe);
        WebElement body = driver.findElement(By.tagName("body"));
        body.clear();
        body.click();
        body.sendKeys(text);
        body.sendKeys(Keys.CONTROL + "a");
        driver.switchTo().parentFrame();
        LOG.info("enterText "+text);
        return this;
    }

    public boolean setTextBolt() {
        btnBolt.click();
        try {
            driver.switchTo().frame(iframe);
            boolean isExistB = driver.findElement(By.tagName("strong")).isDisplayed();
            driver.switchTo().parentFrame();
            LOG.warn("setTextBolt "+isExistB);
            return isExistB;
        } catch (RuntimeException e) {
            LOG.warn("setTextBolt false");
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean setTextItalic() {
        btnItalic.click();
        try {
            driver.switchTo().frame(iframe);
            boolean isExistI = driver.findElement(By.tagName("em")).isDisplayed();
            driver.switchTo().defaultContent();
            LOG.warn("setTextItalic "+isExistI);
            return isExistI;
        } catch (RuntimeException e) {
            LOG.warn("setTextItalic false");
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean setTextUnderline() {
        btnUnderline.click();
        try {
            driver.switchTo().frame(iframe);
            boolean isExistU = driver.findElement(By.tagName("u")).isDisplayed();
            driver.switchTo().defaultContent();
            LOG.warn("setTextUnderline "+isExistU);
            return isExistU;
        } catch (RuntimeException e) {
            LOG.warn("setTextUnderline false");
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean downloadPortfolio() {
        linkDowloadPortfolio.click();
        String currentUrl = driver.getCurrentUrl();
        driver.navigate().back();

        LOG.warn("downloadPortfolio "+currentUrl.contains(".pdf"));
        return currentUrl.contains(".pdf");
    }

    public boolean isPortfolioPage() {
        try {
            wait.until(ExpectedConditions.urlContains("portfolio"));
            LOG.warn("isPortfolioPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isPortfolioPage false");
            return false;
        }
    }
}
