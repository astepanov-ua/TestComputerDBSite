import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract class BasicPage {
    WebDriver webDriver;
    WebDriverWait wait;

    public BasicPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10, 500);
    }

    public List<WebElement> getElements(By by) {
        waitForElements(by);
        return webDriver.findElements(by);
    }


    public void setValue(String name, By byElement) {
        WebElement search = getElement(byElement);
        int i = 0;
        do {
            search.click();
            search.clear();
            search.sendKeys(name);
            i++;
        } while (i < 100 && !search.getAttribute("value").equals(name));
    }

    public WebElement getElement(By by) {
        waitForElement(by);
        return webDriver.findElement(by);
    }

    public void waitForElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForElements(By by) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
    }
}