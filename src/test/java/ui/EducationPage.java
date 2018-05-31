package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class EducationPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(EducationPage.class);

    @FindBy(xpath = "//div[@title='Название дисциплины']")
    private WebElement nameSubject;
    @FindBy(xpath = "//div[@class='cu-st-discipline-content']")
    private WebElement divInfoSubject;
    @FindBy(xpath = "//a[text()='Написать сообщение']")
    private WebElement btnSentMessange;
    @FindBy(xpath = "//a[text()='Перейти к изучению']")
    private WebElement linkSubject;

    @FindBy(xpath = "//div[./div[text()='Таблица видов учебной деятельности']]")
    private WebElement divTable;
    @FindBy(xpath = "//div[./div[text()='Статистика отправки работ']]")
    private WebElement divStatistic;
    @FindBy(xpath = "//div[./div[text()='Структура курса']]")
    private WebElement divStructure;
    @FindBy(xpath = "//div[@class='b-tree co-st-tree-list']//a")
    private WebElement linkDocument;

    @FindBy(xpath = "//div[@class='b-widget-head js-co-head__fixed']")
    private WebElement titleDocument;

    public EducationPage(WebDriver driver) {
        super(driver);
    }

    public Page openSemester(int number) {
        LOG.info("openSemester "+number);
        driver.findElement(By.xpath("//a[text()='" + number + " семестр ']")).click();
        return this;
    }

    public Page openInfoSubject() {
        LOG.info("openInfoSubject");
        nameSubject.click();

        wait.until(ExpectedConditions.visibilityOf(btnSentMessange));
        return this;
    }
    public boolean isInfoBlockSubjectPresent(){
        try {
            LOG.warn("isInfoBlockSubjectPresent "+divInfoSubject.isDisplayed());
            return divInfoSubject.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isInfoBlockSubjectPresent false");
            return false;
        }
    }

    public boolean writeMessange() {
        LOG.info("writeMessange");
        String fio = btnSentMessange.findElement(By.xpath("../preceding-sibling::td[1]")).getText();
        btnSentMessange.click();
        LOG.info(fio);

        wait.until(webDriver -> new MessagesPage(webDriver).isMessagesPage());
        return new MessagesPage(driver).isOpenDialogueWith(fio);
    }

    public boolean openSubject() {

        String name = nameSubject.getText();
        linkSubject.click();
        try {
            boolean isDisplayed=wait.until(webDriver -> webDriver.findElement(By.xpath("//li[contains(text(),'" + name + "')]"))
                    .isDisplayed());
            LOG.warn("openSubject "+isDisplayed);
            return isDisplayed;
        } catch (RuntimeException e) {
            LOG.warn("openSubject false");
            return false;
        }

    }

    public boolean isTablePresent() {
        try {
            LOG.warn("isTablePresent "+divTable.isDisplayed());
            return divTable.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isTablePresent false");
            return false;
        }
    }

    public boolean isStatisticPresent() {
        try {
            LOG.warn("isStatisticPresent "+divStatistic.isDisplayed());
            return divStatistic.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isStatisticPresent false");
            return false;
        }
    }

    public boolean isStructurePresent() {
        try {
            LOG.warn("isStructurePresent "+divStructure.isDisplayed());
            return divStructure.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isStructurePresent false");
            return false;
        }
    }

    public boolean openDocument() {
        String titleDocumentS = linkDocument.getText();
        linkDocument.click();
        LOG.info("openDocument "+titleDocumentS);
        wait.until(ExpectedConditions.visibilityOf(titleDocument));
        boolean isEquals=titleDocumentS.equals(titleDocument.getText());
        driver.navigate().back();
        return isEquals;
    }

    public boolean isEducationPage() {
        try {
            wait.until(ExpectedConditions.urlContains("curriculum"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Обучение' and @class='active']")));
            LOG.warn("isEducationPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isEducationPage false");
            return false;
        }
    }
}
