package webdriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverLogger extends AbstractWebDriverEventListener {
    private static final Logger LOG = Logger.getLogger(WebDriverLogger.class);

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        LOG.info("WebDriver navigated to '" + url + "'");
    }
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.debug("WebDriver find by element - "
                + by.toString());
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOG.debug("WebDriver click on element - "
                + elementDescription(element));
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        LOG.debug("WebDriver will change value for element - "
                + elementDescription(element));
    }

    private String elementDescription(WebElement element) {
        String description = "tag:" + element.getTagName();
        if (element.getAttribute("id") != null) {
            description += " id: " + element.getAttribute("id");
        }
        else if (element.getAttribute("name") != null) {
            description += " name: " + element.getAttribute("name");
        }

        description += " ('" + element.getText() + "')";

        return description;
    }
}
