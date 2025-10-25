package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {

        String path = System.getProperty("user.dir")+"/src/test/resources/TestData_User.xlsx";
        ExcelUtils xl = new ExcelUtils(path);

        int rownum = xl.getRowCount(path,"Sheet1");
        int colnum = xl.getCellCount(path,"Sheet1",1);

        String apiData[][] = new String[rownum][colnum];

        for (int i=1;i<rownum;i++){

            for(int j=0; j<colnum;j++){
                apiData[i-1][j]= xl.getCellData(path,"Sheet1",i,j);
            }
        }
        return apiData;

    }

    @DataProvider(name = "usernames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir")+"/src/test/resources/TestData_User.xlsx";
        ExcelUtils xl = new ExcelUtils(path);

        int rownum = xl.getRowCount(path,"Sheet1");

        String apiData[] = new String[rownum];
        for (int i=1; i<rownum;i++){
            apiData[i-1] = xl.getCellData(path,"Sheet1",i,1);
        }
        return apiData;
    }

}
