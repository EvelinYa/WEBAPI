import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParametrizedTest extends BaseTest{

        @Test(dataProvider = "searchQueries")
        public void testGithubSearch(String searchQuery) {
            HomePage homePage = new HomePage(driver);
            homePage.searchForValue(searchQuery);
        }

        @DataProvider(name = "searchQueries")
        public Object[][] searchQueries() {
            return new Object[][] {
                    { "Selenium WebDriver" },
                    { "GitHub API" },
                    { "Java programming" }
            };
        }
}