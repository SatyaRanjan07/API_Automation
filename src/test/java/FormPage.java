import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FormPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    @BeforeTest
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));

    }
    @Test
    public void fillForm(){
        WebElement fnameBox = driver.findElement(By.id("firstName"));
        fnameBox.sendKeys("SatyaRanjan");
        WebElement lnameBox = driver.findElement(By.id("lastName"));
        lnameBox.sendKeys("Acharya");
//        List<WebElement> radios = driver.findElements(By.xpath("//label[contains(@for,'gender-radio')]"));
//        WebElement maleRadio = wait.until(
//                ExpectedConditions.elementToBeClickable(By.id("gender-radio-1"))
//        );
        WebElement maleRadio = driver.findElement(By.cssSelector(".custom-control-label"));
        js.executeScript("arguments[0].scrollIntoView(true);",maleRadio);
        maleRadio.click();

        WebElement mnoBox = driver.findElement(By.id("userNumber"));
        mnoBox.sendKeys("9658706934");

        List<WebElement> hobbies = driver.findElements(By.xpath("//label[contains(@for,'hobbies-checkbox-')]"));
        for (WebElement hb:hobbies){
            hb.click();
        }

         WebElement stateDrpdwn = driver.findElement(By.id("state"));
        js.executeScript("arguments[0].scrollIntoView(true);",stateDrpdwn);
         stateDrpdwn.click();
//         WebElement stateSelect = driver.findElement(By.id("react-select-3-option-2"));
        WebElement stateSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select-3-option') and text()='Uttar Pradesh']")));
         stateSelect.click();
//         List<WebElement> options = driver.findElements(By.xpath("//div[contains(@id,\"react-select-3\")]"));
//         for(WebElement op:options){
//             if(op.getText().equals("Haryana")){
//                 op.click();
//                 break;
//             }
//         }

         WebElement cityDrpDwn = driver.findElement(By.id("city"));
        js.executeScript("arguments[0].scrollIntoView(rue);",cityDrpDwn);
         if(cityDrpDwn.isDisplayed()){
             cityDrpDwn.click();
//             List<WebElement> options1 = driver.findElements(By.xpath("//div[contains(@id,\"react-select-4\")]"));
//             for(WebElement op:options1){
//                 if(op.getText().equals("Panipat")){
//                     op.click();
//                     break;
//                 }
//             }
             WebElement citySelect = driver.findElement(By.id("react-select-4-option-2"));
             citySelect.click();

         }


    }
    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}


