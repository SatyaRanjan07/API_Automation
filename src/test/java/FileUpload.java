import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FileUpload extends BaseTest {
    @Test
    public void doFileUpload() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadBtn = driver.findElement(By.id("uploadFile"));
        uploadBtn.sendKeys("C:\\Users\\ASUS\\Downloads\\munnar.png");

        WebElement path = driver.findElement(By.id("uploadedFilePath"));
//        js.executeScript("arguments[0].scrollIntoView(true);", path);
        js.executeScript("window.scrollTo(0,100);");
        String expectedText = "C:\\fakepath\\munnar.png";
        String actualTest = path.getText();
        Assert.assertEquals(actualTest, expectedText,"path didnt matched");
        Thread.sleep(4000);
    }
}
