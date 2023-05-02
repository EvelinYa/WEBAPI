import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static helper.ColorPrinter.printColorMessage;

public class ProfilePage extends BasePage {
    private final static String TITLE = "ProfilePage";

    public ProfilePage(WebDriver driver) {
        super(driver, TITLE);
    }

    public ProjectPage goToProjectPage(String locator) {
        WebElement findLocator = driver.findElement(By.xpath(locator));
        findLocator.click();
        printColorMessage("User navigated Project Page", log, Level.INFO);
        return new ProjectPage(driver);
    }
}