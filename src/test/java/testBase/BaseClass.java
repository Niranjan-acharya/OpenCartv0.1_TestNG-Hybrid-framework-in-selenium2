package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import factory.DriverFactory;
import utilities.ConfigReader;

public class BaseClass {

  public static WebDriver driver; // as we create BaseClass object, creates new driver hence static
  //  public Logger logger; // org.apache.logging.log4j.Logger;
  public Properties prop;

  @BeforeClass(groups = { "sanity", "regression", "datadriven", "master" })
  @Parameters({ "os", "browser" })
  public void setup(String os, String browser) throws IOException {
    //    logger = LogManager.getLogger(this.getClass()); // org.apache.logging.log4j.LogManager;

    driver = DriverFactory.initializeDriver(os, browser);
    System.out.println("Driver :: " + driver);
    prop = ConfigReader.getProperties();

    driver.get(prop.getProperty("appUrl"));
    driver.manage().window().maximize();
  }

  @AfterClass(groups = { "sanity", "regression", "datadriven", "master" })
  public void tearDown() {
    driver.quit();
  }

  public String captureScreen(String tname) {
    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
    File targetFile = new File(targetFilePath);

    sourceFile.renameTo(targetFile);

    return targetFilePath;
  }

  public String randomString() {
    String generatedString = RandomStringUtils.randomAlphabetic(5);
    return generatedString;
  }

  public String randomNumber() {
    String generatedNumber = RandomStringUtils.randomNumeric(10);
    return generatedNumber;
  }

  public String randomAlphaNumber() {
    String generatedAlphaNumber = RandomStringUtils.randomAlphanumeric(5);
    return generatedAlphaNumber;
  }
}
