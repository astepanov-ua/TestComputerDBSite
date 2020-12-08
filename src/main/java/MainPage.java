import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasicPage {

    private final static String URL = "http://computer-database.gatling.io/computers";
    private By addNewPCButton = By.id("add");
    private By searchBox = By.id("searchbox");
    private By searchButton = By.id("searchsubmit");
    private By alertPCAdded = By.className("alert-message");
    private By emptySearchResult = By.className("well");
    private By PCList = By.cssSelector("td>a");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Navigate to the web site: " + URL)
    void open() {
        webDriver.get(URL);
    }

    @Step("Wait page to load ")
    public void pageLoading() {
        waitForElement(addNewPCButton);
    }

    @Step("Checking messege <Nothing to display>")
    public boolean notFoundSuchPC() {
        waitForElement(emptySearchResult);
        return getElement(emptySearchResult).isDisplayed();
    }

    @Step("Checking messege <Done ! Computer asd has been created>")
    public boolean isAlertPresent() {
        waitForElement(alertPCAdded);
        return getElement(alertPCAdded).isDisplayed();
    }

    @Step("Get List of PC names")
    public List<String> filter(String name) {
        setValue(name, searchBox);
        List<String> result = new ArrayList<>();
        getElement(searchButton).click();
        try {
            List<WebElement> elements = getElements(PCList);
            for (WebElement e : elements) {
                result.add(e.getText());
            }
        } catch (Exception e) {
        }
        return result;
    }

    @Step("Click Button <Add a new computer>")
    void clickAddPC() {
        webDriver.findElement(addNewPCButton).click();
    }
}
