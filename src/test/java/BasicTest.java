import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BasicTest {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected String url = "http://computer-database.gatling.io/computers";

    @BeforeSuite
    public void init() {
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 5, 100);
        webDriver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
