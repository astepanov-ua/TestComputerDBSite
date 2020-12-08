import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddingPage extends BasicPage {
    private By inputPCname = By.name("name");
    private By inputDateIntroduced = By.id("introduced");
    private By inputDateDiscontinued = By.id("discontinued");
    private By companyChooser = By.id("company");
    private By companyList = By.tagName("option");
    private By createThisPCButton = By.xpath("//input[@value='Create this computer']");

    public AddingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Wait page to load")
    public void pageLoading() {
        waitForElement(inputPCname);
    }

    @Step("Fill PC data in form")
    void addNewPC(String name, String dateIntroduced, String dateDiscontinued) {
        setValue(name, inputPCname);
        setValue(dateIntroduced, inputDateIntroduced);
        setValue(dateDiscontinued, inputDateDiscontinued);

        WebElement companyForChoose = getElement(companyChooser);
        companyForChoose.click();
        companyForChoose.findElements(companyList).stream().skip(1).findFirst().get().click();

        getElement(createThisPCButton).click();
    }
}
