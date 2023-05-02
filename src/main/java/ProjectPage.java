import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static helper.ColorPrinter.printColorMessage;

public class ProjectPage extends BasePage {
    protected WebElement issuesTab = driver.findElement(By.id("issues-tab"));
    private final static String TITLE = "ProjectPage";

    public ProjectPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public IssuesPage goToIssuesPage() {
        Assert.assertTrue(issuesTab.isDisplayed(), "Issues tab is not displayed");
        issuesTab.click();
        printColorMessage("User navigated Issues Page", log, Level.INFO);
        return new IssuesPage(driver);
    }
}