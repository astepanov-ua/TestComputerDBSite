import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class SiteTest extends BasicTest {

    @Test
    public void addPC() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
        mainPage.pageLoading();
        mainPage.clickAddPC();

        AddingPage addingPage = new AddingPage(webDriver);
        addingPage.pageLoading();

        String name = "NewPC" + Long.toHexString(Double.doubleToLongBits(Math.random()));
        String dateIntr = LocalDate.now().minusYears(10).toString();
        String dateDisc = LocalDate.now().toString();

        addingPage.addNewPC(name, dateIntr, dateDisc);
        Assert.assertTrue(mainPage.isAlertPresent(), "Alert was not found");
        List<String> resultSearchList = mainPage.filter(name);
        if (resultSearchList.isEmpty()) {
            Assert.assertTrue(mainPage.notFoundSuchPC(), "Message was not found");
        }
        Assert.assertTrue(resultSearchList.stream().anyMatch(s -> s.trim().contains(name)), "PC not found");
    }
}
