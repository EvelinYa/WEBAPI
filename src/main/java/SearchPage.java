import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage{
    private final static String TITLE = "SearchPage";

    public SearchPage(WebDriver driver) {
        super(driver, TITLE);
    }
    protected WebElement searchResults = driver.findElement(By.xpath("//h3[contains(text(),'repository results')]"));
}
