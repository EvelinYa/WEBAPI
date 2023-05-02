import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static helper.ColorPrinter.printColorMessage;

public class HomePage extends BasePage {
    private final WebElement signInButton = driver.findElement(By.partialLinkText("Sign in"));
    private final static String TITLE = "HomePage";

    public HomePage(WebDriver driver) {
        super(driver, TITLE);
    }

    public LoginPage goToLoginPage() {
        signInButton.click();
        printColorMessage("User navigated Login Page", log, Level.INFO);
        return new LoginPage(driver);
    }
}