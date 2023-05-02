import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static helper.ColorPrinter.printColorMessage;

public class IssuesPage extends BasePage {

    private final WebElement filtersButton = driver.findElement(By.xpath("//summary[contains(text(),'Filters')]"));
    private final WebElement labelsButton = driver.findElement(By.partialLinkText("Labels"));
    private final WebElement milestonesButton = driver.findElement(By.partialLinkText("Milestones"));
    protected WebElement newIssueButton = driver.findElement(By.xpath("//span[text()='New issue']//parent::a"));
    private final WebElement selectAllIssuesCheckbox = driver.findElement(By.xpath("//input[@aria-label='Select all issues']"));
    private final WebElement openIssuesFilter = driver.findElement(By.xpath("//div[@id='js-issues-toolbar']/div[2]//a[@data-ga-click='Issues, Table state, Open']"));
    private final WebElement closedIssuesFilter = driver.findElement(By.xpath("//div[@id='js-issues-toolbar']/div[2]//a[@data-ga-click='Issues, Table state, Closed']"));
    private final WebElement authorFilter = driver.findElement(By.xpath("//summary[contains(text(),'Author')]"));
    private final WebElement labelFilter = driver.findElement(By.xpath("//summary[@data-ga-click='Issues, Table filter, Label']"));
    private final WebElement projectsFilter = driver.findElement(By.xpath("//summary[@data-ga-click='Issues, Table filter, Projects']"));
    private final WebElement milestonesFilter = driver.findElement(By.xpath("//summary[@data-ga-click='Issues, Table filter, Milestones']"));
    private final WebElement assigneeFilter = driver.findElement(By.xpath("//summary[@data-ga-click='Issues, Table filter, Assignee']"));
    private final WebElement sortFilter = driver.findElement(By.xpath("//summary[contains(text(),'Sort')]"));
    private final static String TITLE = "IssuesPage";


    IssuesPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public void elementsDefaultDisplay() {
        printColorMessage("Check of Default Elements Display has been started.", log, Level.INFO);
        Assert.assertTrue(filtersButton.isDisplayed(), "Filters Button is invisible");
        Assert.assertTrue(labelsButton.isDisplayed(), "Labels Button is invisible");
        Assert.assertTrue(milestonesButton.isDisplayed(), "Milestones Button is invisible");
        Assert.assertTrue(newIssueButton.isDisplayed(), "New Issue Button is invisible");
        Assert.assertTrue(selectAllIssuesCheckbox.isDisplayed(), "Select All Issues Checkbox is invisible");
        Assert.assertTrue(openIssuesFilter.isDisplayed(), "Open Issues Filter is invisible");
        Assert.assertTrue(authorFilter.isDisplayed(), "Author Filter is invisible");
        Assert.assertTrue(labelFilter.isDisplayed(), "Label Filter is invisible");
        Assert.assertTrue(projectsFilter.isDisplayed(), "Projects Filter is invisible");
        Assert.assertTrue(milestonesFilter.isDisplayed(), "Milestones Filter is invisible");
        Assert.assertTrue(assigneeFilter.isDisplayed(), "Assignee Filter is invisible");
        Assert.assertTrue(sortFilter.isDisplayed(), "Sort Filter is invisible");
        printColorMessage("Default display of Elements has been checked.", log, Level.INFO);
    }

    public void issueTitleDisplayAndWork(String issueTitleXPath, String issueTitleXPAthOnIssuePage) {
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement issueTitle = driver.findElement(By.xpath(issueTitleXPath));
        Assert.assertTrue(issueTitle.isDisplayed(), "Issue Title is invisible");
        issueTitle.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement issueNameOnIssuePage = driver.findElement(By.xpath(issueTitleXPAthOnIssuePage));
        Assert.assertTrue(issueNameOnIssuePage.isDisplayed(), "Issue Name On Issue Page is invisible");
        WebElement issuesTab = driver.findElement(By.id("issues-tab"));
        issuesTab.click();
        printColorMessage("Issue Title is Displayed And Work", log, Level.INFO);
    }

    public void issueLabelDisplay(String LabelXPath) {
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement label = driver.findElement(By.xpath(LabelXPath));
        Assert.assertTrue(label.isDisplayed(), "Issue Label is invisible");
        printColorMessage("Issue Label is Displayed", log, Level.INFO);
    }

    public void issueMilestoneDisplayAndWork(String issueMilestoneXPath, String milestoneXPAthOnIssuePage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement milestone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(issueMilestoneXPath)));
        milestone.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement milestoneName = driver.findElement(By.xpath(milestoneXPAthOnIssuePage));
        Assert.assertTrue(milestoneName.isDisplayed(), "Issue Milestone Name is invisible");
        WebElement issuesTab = driver.findElement(By.id("issues-tab"));
        issuesTab.click();
        printColorMessage("Issue Milestone is Displayed and Work", log, Level.INFO);
    }

    public void issueUserAvatarDisplay(String userAvatarXPath) {
        WebElement userAvatar = driver.findElement(By.xpath(userAvatarXPath));
        Assert.assertTrue(userAvatar.isDisplayed(), "Issue User Avatar is invisible");
        printColorMessage("Issue User Avatar is Displayed is Displayed", log, Level.INFO);
    }

    public void issueCommentDisplay(String commentXPath) {
        WebElement issueComment = driver.findElement(By.xpath(commentXPath));
        Assert.assertTrue(issueComment.isDisplayed(), "Issue Comment is invisible");
        printColorMessage("Issue Comment is Displayed", log, Level.INFO);
    }

    public void noOpenIssues() {
        WebElement noOpenIssuesText = driver.findElement(By.xpath("//h3[text()='There aren’t any open issues.']"));
        Assert.assertTrue(noOpenIssuesText.isDisplayed(), "No Open Issues text is invisible");
        Assert.assertEquals("There aren’t any open issues.", noOpenIssuesText.getText(), "No Open Issues text is incorrect");
        gitHubTextInfo();
        checkSearchValue("//input[@value='is:issue is:open ']");
        printColorMessage("'0 Open' issues is Displayed", log, Level.INFO);
    }

    public void noClosedIssues() {
        Assert.assertTrue(closedIssuesFilter.isDisplayed(), "Closed Issues Filter is invisible");
        closedIssuesFilter.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement noClosedIssuesText = driver.findElement(By.xpath("//h3[contains(text(),'No results ')]"));
        Assert.assertTrue(noClosedIssuesText.isDisplayed(), "No Closed Issues text is invisible");
        Assert.assertEquals("No results matched your search.", noClosedIssuesText.getText(), "No Open Issues text is incorrect");
        gitHubTextInfo();
        clearSearchLinkDisplay();
        checkSearchValue("//input[@value='is:issue is:closed ']");
        printColorMessage("'0 Closed' issues is Displayed", log, Level.INFO);
    }

    public void clearSearchLinkDisplay() {
        WebElement clearSearchQuery = driver.findElement(By.partialLinkText("Clear current "));
        Assert.assertTrue(clearSearchQuery.isDisplayed(), "Clear Search Query text is invisible");
        Assert.assertEquals("Clear current search query, filters, and sorts", clearSearchQuery.getText(), "Clear Search Query text is incorrect");
        printColorMessage("\"Clear current search query, filters, and sorts\" is displayed", log, Level.INFO);

    }

    public void gitHubTextInfo() {
        WebElement noIssuesIcon = driver.findElement(By.cssSelector(".blankslate-icon"));
        Assert.assertTrue(noIssuesIcon.isDisplayed(), "No Issues Icon is invisibe");
        WebElement allOfGitHubLink = driver.findElement(By.linkText("all of GitHub"));
        Assert.assertTrue(allOfGitHubLink.isDisplayed(), "'allOfGitHub' link is invisible");
        WebElement advancedSearchLink = driver.findElement(By.linkText("advanced search"));
        Assert.assertTrue(advancedSearchLink.isDisplayed(), "'advancedSearch' link is invisible");
        WebElement youCouldSearchText = driver.findElement(By.xpath("//p[contains(text(),'You could search ')]"));
        Assert.assertTrue(youCouldSearchText.isDisplayed(),"'YouCouldSearch..' text is invisible");
        Assert.assertEquals("You could search all of GitHub or try an advanced search.", youCouldSearchText.getText(), "gitHubTextInfo is incorrect");
        printColorMessage("\"You could search all of GitHub or try an advanced search.\" text with links is displayed", log, Level.INFO);
    }

    public void checkSearchValue(String locator) {
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        WebElement inputValue = driver.findElement(By.xpath(locator));
        Assert.assertTrue(inputValue.isDisplayed(), "Input value is invisible");
        printColorMessage("Search Input Value is checked", log, Level.INFO);
    }

    public String issuesTabCounter() {
        WebElement IssueTabCounter = driver.findElement(By.xpath("//a[@id='issues-tab']/span[2]"));
        Assert.assertTrue(IssueTabCounter.isDisplayed(), "Issue Tab Counter is invisible");
        printColorMessage("Issues Tab Counter is checked", log, Level.INFO);
        return IssueTabCounter.getText();
    }

    public String labelsButtonCounter() {
        WebElement labelsButtonCounter = driver.findElement(By.partialLinkText("Labels"));
        Assert.assertTrue(labelsButtonCounter.isDisplayed(), "Labels Button Counter is invisible");
        printColorMessage("Labels Button Counter is checked", log, Level.INFO);
        return labelsButtonCounter.getText();
    }

    public String milestonesButtonCounter() {
        WebElement milestonesButtonCounter = driver.findElement(By.partialLinkText("Milestones"));
        Assert.assertTrue(milestonesButtonCounter.isDisplayed(), "Milestones Button Counter is invisible");
        printColorMessage("Milestones Button Counter is checked", log, Level.INFO);
        return milestonesButtonCounter.getText();
    }

    public String openIssuesCounter() {
        WebElement openIssuesFilterCounter = driver.findElement(By.xpath("//div[@id='js-issues-toolbar']/div[2]//a[@data-ga-click='Issues, Table state, Open']"));
        Assert.assertTrue(openIssuesFilterCounter.isDisplayed(), "Open Issues Filter Counter is invisible");
        printColorMessage("Open Issues Counter is checked", log, Level.INFO);
        return openIssuesFilterCounter.getText();
    }

    public String closedIssuesCounter() {
        WebElement closedIssuesFilterCounter = driver.findElement(By.xpath("//div[@id='js-issues-toolbar']/div[2]//a[@data-ga-click='Issues, Table state, Closed']"));
        Assert.assertTrue(closedIssuesFilterCounter.isDisplayed(), "Closed Issues Filter Counter is invisible");
        printColorMessage("Closed Issues Counter is checked", log, Level.INFO);
        return closedIssuesFilterCounter.getText();
    }

    public IssuePage goToAddIssuePage() {
        WebElement newIssueButton = driver.findElement(By.xpath("//span[text()='New issue']//parent::a"));
        newIssueButton.click();
        printColorMessage("User navigated Add Issue Page", log, Level.INFO);
        return new IssuePage(driver);
    }

    public void removeIssueFromIssuesPage(String issueTitleXPath) {
        WebElement issueTitle = driver.findElement(By.xpath(issueTitleXPath));
        issueTitle.click();
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        IssuePage issuePage = new IssuePage(driver);
        issuePage.removeIssue();
        printColorMessage("User Removed Issue from Issues Page", log, Level.INFO);
    }
}