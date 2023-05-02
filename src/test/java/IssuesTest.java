import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IssuesTest extends BaseTest {
    @Test
    public void issuesPageDefaultValuesTest() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successfulLogin("nnnnnnnnn", "nnnnnnnnnn");
        MainPage mainPage = new MainPage(driver);
        mainPage.goToMenuPage();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.goToProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.goToProjectPage("//span[text()='myFirstProject']//parent::a");
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.goToIssuesPage();
        IssuesPage issuesPage = new IssuesPage(driver);
        issuesPage.elementsDefaultDisplay();
        issuesPage.noOpenIssues();
        issuesPage.noClosedIssues();
        Assertions.assertEquals("Labels 9", issuesPage.labelsButtonCounter());
        Assertions.assertEquals("Milestones 1", issuesPage.milestonesButtonCounter());
        Assertions.assertEquals("0 Open", issuesPage.openIssuesCounter());
        Assertions.assertEquals("0 Closed", issuesPage.closedIssuesCounter());
    }

    @Test
    public void addIssueAllFieldsFilledAndCommentTest() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successfulLogin("evelinka830@gmail.com", "Evelina1234Test");
        MainPage mainPage = new MainPage(driver);
        mainPage.goToMenuPage();
        MenuPage menuPage = new MenuPage(driver);
        menuPage.goToProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.goToProjectPage("//span[text()='myFirstProject']//parent::a");
        ProjectPage projectPage = new ProjectPage(driver);
        projectPage.goToIssuesPage();
        IssuesPage issuesPage = new IssuesPage(driver);
        issuesPage.goToAddIssuePage();
        IssuePage issuePage = new IssuePage(driver);
        issuePage.addIssueWithAllFieldsAndComment(
                "Test Issue 1",
                "Test Issue description 1",
                "//span[@class='js-username' and text()='EvelinYa']",
                "//label[@data-prio-filter-value='bug'][1]",
                "//label[@title='Test Project 2']",
                "//span[text()='Milestone1']//ancestor::label",
                "Test Issue 1 comment 1"
        );
        Assertions.assertEquals("1", issuesPage.issuesTabCounter());
        Assertions.assertEquals("1 Open", issuesPage.openIssuesCounter());

        issuesPage.issueTitleDisplayAndWork(
                "//a[text()='Test Issue 1']",
                "//bdi[text()='Test Issue 1']");

        issuesPage.issueLabelDisplay(
                "//a[text()='Test Issue 1']//parent::div//a[contains(text(),'bug')]");

        issuesPage.issueMilestoneDisplayAndWork(
                "//a[text()='Test Issue 1']//parent::div//span[contains(text(),'Milestone1')]",
                "//h2[text()='Milestone1']");

        issuesPage.issueUserAvatarDisplay(
                "//a[text()='Test Issue 1']//parent::div/following-sibling::div//img[@class='from-avatar avatar-user']");

        issuesPage.issueCommentDisplay(
                "//a[text()='Test Issue 1']//parent::div/following-sibling::div/span/a[@aria-label='1 comment']");

        issuesPage.removeIssueFromIssuesPage("//a[text()='Test Issue 1']");

    }
}