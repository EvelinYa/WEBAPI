import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static helper.ColorPrinter.printColorMessage;

public class IssuePage extends BasePage {
    private final WebElement assigneesField = driver.findElement(By.xpath("//summary[@data-menu-trigger='assignees-select-menu']"));
    private final WebElement labelsField = driver.findElement(By.xpath("//summary[@data-menu-trigger='labels-select-menu']"));
    private final WebElement projectsField = driver.findElement(By.xpath("//summary[@data-menu-trigger='projects-select-menu']"));
    private final WebElement milestonesField = driver.findElement(By.xpath("//summary[@data-menu-trigger='milestone-select-menu']"));
    private final static String TITLE = "IssuePage";

    IssuePage(WebDriver driver) {
        super(driver, TITLE);
    }

    public void addIssueWithAllFieldsAndComment(String issueTitle, String IssueDescription, String AssigneeXPath, String LabelXPath, String ProjectXPath, String MilestoneName, String commentText) {
        printColorMessage("Adding Issue with all Fields filled And Comment added has been started.", log, Level.INFO);
        WebElement titleField = driver.findElement(By.xpath("//input[@placeholder='Title']"));
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        titleField.sendKeys(issueTitle);
        WebElement descriptionField = driver.findElement(By.id("issue_body"));
        descriptionField.sendKeys(IssueDescription);
        chooseAssignee(AssigneeXPath);
        chooseLabels(LabelXPath);
        chooseProjects(ProjectXPath);
        chooseMilestones(MilestoneName);
        submitNewIssueCreation();
        addComment(commentText);
        printColorMessage("Adding Issue with all Fields filled And Comment added has been finished.", log, Level.INFO);

    }

    public void chooseAssignee(String AssigneeNameXPath) {
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        assigneesField.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement assigneeName = driver.findElement(By.xpath(AssigneeNameXPath));
        assigneeName.click();
        assigneesField.click();
        printColorMessage("Issue Assignee is chosen", log, Level.INFO);
    }

    public void chooseLabels(String LabelXPath) {
        labelsField.click();
        WebElement labelType = driver.findElement(By.xpath(LabelXPath));
        labelType.click();
        labelsField.click();
        printColorMessage("Issue Label is chosen", log, Level.INFO);

    }

    public void chooseProjects(String ProjectXPath) {
        projectsField.click();
        WebElement projectName = driver.findElement(By.xpath(ProjectXPath));
        projectName.click();
        projectsField.click();
        printColorMessage("Issue Project is chosen", log, Level.INFO);
    }

    public void chooseMilestones(String MilestoneName) {
        milestonesField.click();
        WebElement milestonesName = driver.findElement(By.xpath(MilestoneName));
        milestonesName.click();
        milestonesField.click();
        printColorMessage("Issue Milestone is chosen", log, Level.INFO);
    }

    public void submitNewIssueCreation() {
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit new issue')]"));
        submitButton.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        printColorMessage("Issue is created. User navigated Add Comment Page", log, Level.INFO);
    }

    public IssuesPage addComment(String commentText) {
        WebElement newComment = driver.findElement(By.id("new_comment_field"));
        newComment.sendKeys(commentText);
        WebElement commentButton = driver.findElement(By.xpath("//button[contains(text(),'Comment')]"));
        commentButton.click();
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.issuesTab.click();
        printColorMessage("User added Issue Comment and navigated Issues Page", log, Level.INFO);
        return new IssuesPage(driver);

    }

    public void removeIssue() {
        WebElement deleteButton = driver.findElement(By.xpath("//strong[text()='Delete issue']//parent::span"));
        deleteButton.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement submitDeleteButton = driver.findElement(By.xpath("//button[contains(text(),'Delete this issue')]"));
        submitDeleteButton.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement submitDeleteMessage = driver.findElement(By.xpath("//div[contains(text(),'The issue was successfully deleted.')]"));
        Assert.assertEquals("The issue was successfully deleted.", submitDeleteMessage.getText());
        printColorMessage("User has removed the Issue from Issue Page", log, Level.INFO);
    }
}