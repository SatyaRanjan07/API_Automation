import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SimpleLogin {
    WebDriver driver;

    @BeforeTest
        public void openBrowser(){
            WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
           driver.manage().window().maximize();
           driver.get("https://the-internet.herokuapp.com/login");
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void succLogin(){
        WebElement usernameBox = driver.findElement(By.id("username"));
        usernameBox.sendKeys("tomsmith");
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys("SuperSecretPassword!");
        WebElement loginBtn = driver.findElement(By.className("radius"));
        loginBtn.click();
    }

    public void unSuccLogin() {
        WebElement usernameBox = driver.findElement(By.id("username"));
        usernameBox.sendKeys("tomsmith");
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys("SuperSecretPassword!a");
        WebElement loginBtn = driver.findElement(By.className("radius"));
        loginBtn.click();
    }
    @Test
    public void testLogin(){
//        try {
            unSuccLogin();
            WebElement errMsg = driver.findElement(By.id("flash"));
            String actualMsg = errMsg.getText().trim();
            actualMsg = actualMsg.replace("×", "").trim();
            String expectedErrMsg = "Your password is invalid!";
            System.out.println(actualMsg);
            Assert.assertEquals(actualMsg ,expectedErrMsg, "msg not matched!");
        }
//        catch (AssertionError e)
//        {
//            System.out.println(e.getMessage());
//        }

    @Test
    public void succTestLogin() throws InterruptedException {
            succLogin();
            WebElement successMsg = driver.findElement(By.id("flash"));
            String actualMsg1 = successMsg.getText().trim();
            actualMsg1 = actualMsg1.replace("×", "").trim();
            System.out.println(actualMsg1);
            String expectedMsg = "You logged into a secure area!";
            Assert.assertEquals(actualMsg1, expectedMsg, "msg not matched!");
        }
        @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
