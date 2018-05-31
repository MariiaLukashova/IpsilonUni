package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonPage extends Page {
    private static final Logger LOG = Logger.getLogger(CommonPage.class);

    @FindBy(xpath = "//a[text()='Главная']")
    private WebElement btnMain;
    @FindBy(xpath = "//a[text()='Информация']")
    private WebElement btnInformation;
    @FindBy(xpath = "//a[text()='Обучение']")
    private WebElement btnEducation;
    @FindBy(xpath = "//a[text()='Одногруппники']")
    private WebElement btnGroupmates;
    @FindBy(xpath = "//a[text()='Сообщения']")
    private WebElement btnMessages;
    @FindBy(xpath = "//a[text()='Тех. поддержка']")
    private WebElement btnSupport;

    @FindBy(xpath = "//div[./i[@class='icon-tag']]")
    private WebElement toggleNote;
    @FindBy(xpath = "//button[text()='Добавить заметку']")
    private WebElement addNote;
    @FindBy(xpath = "//ul[@id='user_notes']")
    private WebElement listNotes;
    @FindBy(id = "note_content")
    private WebElement textareaNote;
    @FindBy(xpath = "//div[@class='f-group-action']/input[@value='Сохранить']")
    private WebElement btnSafeNote;

    @FindBy(xpath = "//span[@class='name-faculty']/./following-sibling::span")
    private WebElement userName;
    @FindBy(xpath = "//a[@id='sign_out']")
    private WebElement linkExit;
    @FindBy(xpath = "//a[contains(@href,'support')]")
    private WebElement linkSupport;


    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public Page openMainPage() {
        LOG.info("openMainPage");
        actions.click(btnMain)
                .build().perform();
        wait.until(webDriver -> new MainPage(webDriver).isMainPage());
        return new MainPage(this.driver);
    }

    public Page openInformationPage() {
        LOG.info("openInformationPage");
        actions.click(btnInformation)
                .build().perform();
        wait.until(webDriver -> new InformationPage(webDriver).isInformationPage());
        return new InformationPage(this.driver);
    }

    public Page openEducationPage() {
        LOG.info("openEducationPage");
        actions.click(btnEducation)
                .build().perform();
        wait.until(webDriver -> new EducationPage(webDriver).isEducationPage());
        return new EducationPage(this.driver);
    }

    public Page openGroupmatesPage() {
        LOG.info("openGroupmatesPage");
        actions.click(btnGroupmates)
                .build().perform();
        wait.until(webDriver -> new GroupmatesPage(webDriver).isGroupmatesPage());
        return new GroupmatesPage(this.driver);
    }

    public Page openMessagesPage() {
        LOG.info("openMessagesPage");
        actions.click(btnMessages)
                .build().perform();
        wait.until(webDriver -> new MessagesPage(webDriver).isMessagesPage());
        return new MessagesPage(this.driver);
    }

    public Page openSupportPage() {
        LOG.info("openSupportPage");
        actions.click(btnSupport)
                .click(linkSupport)
                .build().perform();
        wait.until(webDriver -> new SupportPage(webDriver).isSupportPage());
        return new SupportPage(this.driver);
    }

    public Page logout() {
        actions.click(userName)
                .click(linkExit)
                .build().perform();
        wait.until(webDriver -> new LoginPage(driver).isLoginPage());
        LOG.info("logout");
        return new LoginPage(this.driver);
    }

    public Page addNote(String note) {
        actions.moveToElement(toggleNote).build().perform();
        wait.until(webDriver -> addNote.isDisplayed());
        addNote.click();

        wait.until(ExpectedConditions.visibilityOf(textareaNote));
        actions.sendKeys(textareaNote, note)
                .click(btnSafeNote)
                .build().perform();
        LOG.info("addNote "+note);
        waitIsNotElementPresent(btnSafeNote);
        return this;
    }

    public Page deleteNote(String note) {
        actions.moveToElement(toggleNote).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(addNote));
        WebElement item = driver.findElement(By.xpath("//li[text()='" + note + "']"));
        actions.click(item.findElement(By.xpath("//a[@title='Удалить']")));
        driver.navigate().refresh();
        LOG.info("deleteNote "+note);
        return this;
    }
    public int countNote() {
        LOG.info("countNote");
        return listNotes.findElements(By.tagName("li")).size();
    }
    public boolean isLogin(String username){
        if(userName.getText().equals(username)){
            LOG.warn("isLogin "+username+" true");
            return true;
        }
        else{
            LOG.warn("isLogin "+username+" false");
            return false;
        }
    }

    public boolean isNotePresent(String note) {
        try {
            boolean isEnabled=wait.until( webDriver -> webDriver.
                    findElement(By.xpath("//li[text()='" + note + "']")).isEnabled());
            LOG.warn("isNotePresent "+isEnabled);
            return isEnabled;
        } catch (RuntimeException e) {
            LOG.warn("isNotePresent false");
            return false;
        }
    }
}
