import helper.Level;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static helper.ColorPrinter.printColorMessage;

public class ProjectPage extends BasePage {
    protected WebElement issuesTab = driver.findElement(By.id("issues-tab"));
    private final static String TITLE = "ProjectPage";

    public ProjectPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public IssuesPage goToIssuesPage() {
        Assertions.assertTrue(issuesTab.isDisplayed(), "Issues tab is not displayed");
        issuesTab.click();
        printColorMessage("User navigated Issues Page", log, Level.INFO);
        return new IssuesPage(driver);
    }
}