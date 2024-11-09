package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
  //DataProvider 1 for Login
  @DataProvider(name="LoginData")
  public String[][] getData() throws IOException {
    String path = ".\\testData\\OpenCart_LoginData.xlsx"; //taking xl file data

    ExcelUtility xlutil = new ExcelUtility(path); //creating an obj

    int totalRows = xlutil.getRowCount("Sheet1");
    int totalCols = xlutil.getCellCount("Sheet1", 1);

    System.out.println(totalRows+" :: "+totalCols);

    String loginData[][] = new String[totalRows][totalCols]; //created for two dimension array which can store the data user and password

    for(int i=1; i<=totalRows; i++) { //1 //read the data from xl storing in two deminsional array
      for(int j=0; j<totalCols; j++) { //0 //i is rows j is col
        loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j); //1, 0
        System.out.println(loginData[i-1][j]);
      }
    }

    return loginData; //returns 2D array
  }

  //DataProvider 2, 3, 4,..., n for other test cases

}
