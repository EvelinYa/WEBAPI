import helper.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.ColorPrinter.printColorMessage;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    private final String title;
    protected Logger log;

    public BasePage(WebDriver driver, String title) {
        this.driver = driver;
        this.title = title;
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(1000));
        this.log = LogManager.getLogger(this.title);
        printColorMessage("Page object of " + title, log, Level.DEBUG);
    }
}