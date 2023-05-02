import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static helper.ColorPrinter.printColorMessage;

public class HomePage extends BasePage {
    private final WebElement signInButton = driver.findElement(By.partialLinkText("Sign in"));
    private final static String TITLE = "HomePage";

    public HomePage(WebDriver driver) {
        super(driver, TITLE);
    }

    private WebElement searchInput = driver.findElement(By.xpath("//input[@name='q']"));

    public LoginPage goToLoginPage() {
        signInButton.click();
        printColorMessage("User navigated Login Page", log, Level.INFO);
        return new LoginPage(driver);
    }
    public SearchPage searchForValue(String query){
        searchInput.sendKeys(query);
        searchInput.click();
        WebElement chooseSearchValue = driver.findElement(By.xpath("//ul[@id='jump-to-results']"));
        chooseSearchValue.click();
        SearchPage searchpage = new SearchPage(driver);
        Assert.assertTrue(searchpage.searchResults.isDisplayed());
        return new SearchPage(driver);
    }
}