import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static helper.ColorPrinter.printColorMessage;

public class MainPage extends BasePage {
    private final WebElement userPhoto = driver.findElement(By.xpath("//summary[@aria-label='View profile and more']"));
    private final static String TITLE = "MainPage";

    public MainPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public MenuPage goToMenuPage() {
        userPhoto.click();
        printColorMessage("User navigated User Profile Menu", log, Level.INFO);
        return new MenuPage(driver);
    }
}