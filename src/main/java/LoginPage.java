import helper.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static helper.ColorPrinter.printColorMessage;

public class LoginPage extends BasePage {
    private final WebElement loginField = driver.findElement(By.id("login_field"));
    private final WebElement passwordField = driver.findElement(By.id("password"));
    private final WebElement signInButton = driver.findElement(By.xpath("//input[@value='Sign in']"));
    private final static String TITLE = "LoginPage";


    public LoginPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public MainPage successfulLogin(String login, String password) {
        Assert.assertTrue(loginField.isDisplayed(), "Login field is invisible");
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is invisible");
        Assert.assertTrue(signInButton.isDisplayed(), "Sign in Button field is invisible");
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        signInButton.click();
        printColorMessage("Successful authorization", log, Level.INFO);
        return new MainPage(driver);
    }
}