import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }


}
