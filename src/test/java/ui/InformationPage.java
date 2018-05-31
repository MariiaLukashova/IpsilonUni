package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InformationPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(InformationPage.class);

    @FindBy(xpath = "//div[ ./div[text()='Контакты']]")
    private WebElement divContacts;
    @FindBy(xpath = "//div[ ./div[text()='Документы']]")
    private WebElement divDocuments;
    @FindBy(xpath = "//ul[@class='do-list']//a")
    private WebElement linkDocument;

    public InformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isContactsPresent() {
        try {
            LOG.warn("isContactsPresent "+divContacts.isDisplayed());
            return divContacts.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isContactsPresent false");
            return false;
        }
    }

    public boolean isDocumentsPresent() {
        try {
            LOG.warn("isDocumentsPresent "+divDocuments.isDisplayed());
            return divDocuments.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isDocumentsPresent false");
            return false;
        }
    }

    public boolean openDocument() {
        linkDocument.click();
        String currentUrl = driver.getCurrentUrl();
        driver.navigate().back();

        LOG.warn("openDocument "+currentUrl.contains(".pdf"));
        return currentUrl.contains(".pdf");
    }

    public boolean isInformationPage() {
        try {
            wait.until(ExpectedConditions.urlContains("information"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Информация' and @class='active']")));
            LOG.warn("isInformationPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isInformationPage false");
            return false;
        }
    }
}
