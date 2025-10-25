import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLOutput;

public class DataDriverDemo extends BaseTest{
    @Test
    public void FD_calculator() throws IOException, InterruptedException {

        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        String dataFile = "C:\\Users\\ASUS\\Downloads\\Data1.xlsx";
//        Select select = new Select();
        js.executeScript("window.scrollTo(0,200);");
        int rows = ExcelUtils.getRowCount(dataFile,"Sheet1");
        WebElement principalBox = driver.findElement(By.id("principal"));
        WebElement roiBox = driver.findElement(By.id("interest"));
        WebElement periodBox = driver.findElement(By.id("tenure"));
        WebElement periodDropDown = driver.findElement(By.id("tenurePeriod"));
        WebElement freqDropDwn = driver.findElement(By.id("frequency"));
        WebElement calculateBtn = driver.findElement(By.xpath("//div[contains(@class,'CTR PT15')]//a[1]"));
//        driver.findElement(By.className("wzrk-cancel")).click();

        for(int i=1;i<=rows;i++){
            String principalData = ExcelUtils.getCellData(dataFile,"Sheet1",i,0);
            String roiData = ExcelUtils.getCellData(dataFile,"Sheet1",i,1);
            String periodData = ExcelUtils.getCellData(dataFile,"Sheet1",i,2);
            String periodYearsData = ExcelUtils.getCellData(dataFile,"Sheet1",i,3);
            String freqData = ExcelUtils.getCellData(dataFile,"Sheet1",i,4);
            String expMValueData = ExcelUtils.getCellData(dataFile,"Sheet1",i,5);

            principalBox.clear();
            principalBox.sendKeys(principalData);
            roiBox.clear();
            roiBox.sendKeys(roiData);
            periodBox.clear();
            periodBox.sendKeys(periodData);
            Select select = new Select(periodDropDown);
            select.selectByVisibleText(periodYearsData);
            Select select1 = new Select(freqDropDwn);
            select1.selectByVisibleText(freqData);
            js.executeScript("arguments[0].click();",calculateBtn);
            String actMValue = driver.findElement(By.xpath("//span[@id='resp_matval']//strong")).getText();
//            Double actMValue = Double.parseDouble(expValuetext);
//            Double expMValue = Double.parseDouble();

            System.out.println(actMValue);
            System.out.println(expMValueData);

            if( Double.parseDouble(expMValueData)== Double.parseDouble(actMValue)){
                ExcelUtils.setCellData(dataFile,"Sheet1",i,7,"pass");
            }
            else
            {
                ExcelUtils.setCellData(dataFile,"Sheet1",i,7,"fail");
            }

        }
    }
}
