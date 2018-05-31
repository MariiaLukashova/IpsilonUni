package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class MainPage extends CommonPage {
    private static final Logger LOG = Logger.getLogger(MainPage.class);

    @FindBy(xpath = "//div[@class='us-profile-info']")
    private WebElement divInfo;
    @FindBy(xpath = "//div[@class='b-widget-head' and text()='Новости']")
    private WebElement divNews;

    @FindBy(xpath = "//div[@class='us-profile-avatar']")
    private WebElement avatar;
    @FindBy(xpath = "//a[text()='Изменить фото']")
    private WebElement btnChangeAvatar;
    @FindBy(id = "user_avatar")
    private WebElement btnChooseFile;
    @FindBy(xpath = "//a[text()='Удалить фото']")
    private WebElement btnDeleteImage;
    @FindBy(xpath = "//p[text()='Город:']/a")
    private WebElement linkCity;
    @FindBy(xpath = "//p[text()='Дата рождения:']/a")
    private WebElement linkBirthday;
    @FindBy(xpath = "//p[text()='Телефон:']/a")
    private WebElement linkPhone;
    @FindBy(xpath = "//a[contains(@id,'portfolio')]")
    private WebElement linkPortfolio;
    @FindBy(xpath = "//a[@href='/users/edit']")
    private WebElement linkChangeEmail;
    @FindBy(xpath = "//a[@class='attention']/./preceding-sibling::i")
    private WebElement spanEmail;
    @FindBy(xpath = "//div[@class='b-list-item']//a")
    private WebElement itemNews;
    @FindBy(xpath = "//input[@value='Сохранить']")
    private WebElement btnSafe;

    @FindBy(xpath = "//div[contains(@class,'popover')]")
    private WebElement divPopover;
    @FindBy(xpath = "//select")
    private WebElement select;
    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputText;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnOK;
    @FindBy(xpath = "//button[@type='button']")
    private WebElement btnCancel;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInfoBlockPresent() {
        try {
            LOG.warn("isInfoBlockPresent "+divInfo.isDisplayed());
            return divInfo.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isInfoBlockPresent false");
            return false;
        }
    }

    public boolean isNewsBlockPresent() {
        try {
            LOG.warn("isNewsBlockPresent "+divNews.isDisplayed());
            return divNews.isDisplayed();
        } catch (RuntimeException e) {
            LOG.warn("isNewsBlockPresent false");
            return false;
        }
    }

    public Page changeAvatar(String path) {
        actions.moveToElement(avatar)
                .click(btnChangeAvatar)
                .build().perform();
        wait.until(ExpectedConditions.visibilityOf(btnChooseFile));
        btnChooseFile.sendKeys(new File("").getAbsolutePath()+path);
        actions.click(btnSafe)
                .build().perform();
        LOG.info("changeAvatar "+path);
        waitIsNotElementPresent(btnChooseFile);
        return this;
    }

    public boolean isAvatarPresent(String nameFile) {
        String src = avatar.findElement(By.tagName("img")).getAttribute("src");
        LOG.warn("isAvatarPresent "+nameFile+" "+src.contains(nameFile));
        return src.contains(nameFile);
    }

    public Page deleteAvatar() {
        actions.moveToElement(avatar)
                .click(btnChangeAvatar).build().perform();
        wait.until(ExpectedConditions.visibilityOf(btnDeleteImage)).click();

        wait.until(ExpectedConditions.alertIsPresent()).accept();
        LOG.info("deleteAvatar");
        wait.until(ExpectedConditions.invisibilityOf(btnChooseFile));
        return this;
    }

    public Page changeCity(String value) {
        linkCity.click();
        Select selectCity = new Select(select);
        selectCity.selectByValue(value);
        btnOK.click();
        LOG.info("changeCity "+value);
        waitIsNotElementPresent(divPopover);
        return this;
    }

    public boolean isCityPresent(String city) {
        if (linkCity.getText().equals(city)){
            LOG.warn("isCityPresent "+city+" true");
            return true;
        }
        else{
            LOG.warn("isCityPresent "+city+" false");
            return false;
        }
    }

    public Page changeBirthday(String birthday) {
        LOG.info("changeBirthday "+birthday);
        linkBirthday.click();
        inputText.clear();
        actions.sendKeys(inputText, birthday)
                .click(btnOK)
                .build().perform();
        waitIsNotElementPresent(divPopover);
        return this;
    }

    public boolean isBirthdayPresent(String birthday) {
        if (linkBirthday.getText().equals(birthday)){
            LOG.warn("isBirthdayPresent "+birthday+" true");
            return true;
        }
        else{
            LOG.warn("isBirthdayPresent "+birthday+" "+linkBirthday.getText()+" false");
            return false;
        }
    }

    public Page changePhone(String phone) {
        LOG.info("changePhone "+phone);
        linkPhone.click();
        inputText.clear();
        actions.sendKeys(inputText, phone)
                .click(btnOK)
                .build().perform();

        waitIsNotElementPresent(divPopover);
        return this;
    }

    public boolean isPhonePresent(String phone) {
        if (linkPhone.getText().equals(phone)){
            LOG.warn("isPhonePresent "+phone+" true");
            return true;
        }
        else{
            LOG.warn("isPhonePresent "+phone+" false");
            return false;
        }
    }

    public Page openPortfolio() {
        LOG.info("openPortfolio");
        linkPortfolio.click();
        wait.until(webDriver -> new PortfolioPage(webDriver).isPortfolioPage());
        return new PortfolioPage(driver);
    }

    public Page openNews() {
        LOG.info("openNews");
        itemNews.click();
        wait.until(webDriver -> new NewsPage(webDriver).isNewsPage());
        return new NewsPage(driver);
    }

    public Page openChangeEmailPage() {
        LOG.info("openChangeEmailPage");
        linkChangeEmail.click();
        wait.until(webDriver -> new ChangeEmailPage(webDriver).isChangeEmailPage());
        return new ChangeEmailPage(driver);
    }

    public boolean isEmailPresent(String email) {
        if (spanEmail.getText().equals(email)){
            LOG.warn("isEmailPresent "+email+" true");
            return true;
        }
        else{
            LOG.warn("isEmailPresent false");
            return false;
        }
    }

    public boolean isMainPage() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Главная' and @class='active']")));
            LOG.warn("isMainPage true");
            return true;
        } catch (RuntimeException e) {
            LOG.warn("isMainPage false");
            return false;
        }
    }
}
