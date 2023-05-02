import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static helper.ColorPrinter.printColorMessage;

public class MenuPage extends BasePage {
    private final WebElement yourProfile = driver.findElement(By.linkText("Your profile"));
    private final static String TITLE = "MenuPage";

    public MenuPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public ProfilePage goToProfilePage() {
        yourProfile.click();
        printColorMessage("User navigated User Profile Page", log, Level.INFO);
        return new ProfilePage(driver);
    }
}