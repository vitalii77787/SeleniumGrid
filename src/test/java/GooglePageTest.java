import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.GoogleHomePage;
import pageobjects.GoogleSearchResultsPage;
import utils.BrowserTypes;
import utils.DriverFactory;

import java.net.MalformedURLException;

public class GooglePageTest {
    private DriverFactory driverFactory;
    private int timeOut = 5;

    @BeforeClass
    public void SetUp() throws MalformedURLException {
        driverFactory = new DriverFactory();
        driverFactory.StartBrowser(BrowserTypes.Remote, timeOut);
        driverFactory.GetDriver().navigate().to(Data.BaseUrl);
    }

    @Test
    public void VerifingGoogleSearchPage() {
        //Arrange
        boolean expectedResult = true;

        // Act
        GoogleSearchResultsPage searchResultsPage = TypeResultsInSearchField(Data.TestData1);
        boolean actualResult = searchResultsPage.IsSearchResultLabelPresent();

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

    // Typing google main page input
    private GoogleSearchResultsPage TypeResultsInSearchField(String input) {
        GoogleHomePage homePage = new GoogleHomePage(driverFactory.GetDriver(), driverFactory.getDriverWait());
        homePage.FillInputAndClickEnter(input);
        return new GoogleSearchResultsPage(driverFactory.GetDriver(), driverFactory.getDriverWait());
    }

    @AfterClass
    public void TierDown() {
        driverFactory.StopDriver();
    }
}
