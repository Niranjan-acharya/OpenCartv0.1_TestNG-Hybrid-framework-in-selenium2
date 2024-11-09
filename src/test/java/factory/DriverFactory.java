package factory;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilities.ConfigReader;

public class DriverFactory {

  public static WebDriver driver; // as we create BaseClass object, creates new driver hence static
  public static Logger logger; // org.apache.logging.log4j.Logger;
  public static Properties prop;

  public static WebDriver initializeDriver(String os, String browser) throws IOException {
    // loading config properties
    logger = getLogger();

    prop = ConfigReader.getProperties();
    String executionEnv = prop.getProperty("execution_env");
    //    browser = prop.getProperty("browser").toLowerCase();
    //    os = prop.getProperty("os").toLowerCase();

    System.out.println(os + " :: " + browser);

    // for Grid + Docker
    if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
      System.out.println("REMOTE");

      DesiredCapabilities capabilities = new DesiredCapabilities();
      System.out.println("DesiredCapabilities");

      if (os.equalsIgnoreCase("windows"))
        capabilities.setPlatform(Platform.WIN10);
      else if (os.equalsIgnoreCase("linux"))
        capabilities.setPlatform(Platform.LINUX);
      else if (os.equalsIgnoreCase("mac"))
        capabilities.setPlatform(Platform.MAC);
      else {
        System.out.println("No matching OS");
        return null;
      }

      System.out.println("OS");

      switch (browser.toLowerCase()) {
      case "chrome":
        capabilities.setBrowserName("chrome");
        break;
      case "firefox":
        capabilities.setBrowserName("firefox");
        break;
      case "edge":
        capabilities.setBrowserName("MicrosoftEdge"); // MicrosoftEdge should be same
        break;
      default:
        System.out.println("Invalid Browser Name..");
        return null;
      }

      System.out.println("Browser");

      driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

      System.out.println("WebDriver :: "+driver);
    }

    // for local
    if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {
      System.out.println("LOCAL");
      switch (browser.toLowerCase()) {
      case "chrome":
        driver = new ChromeDriver();
        System.out.println(browser+"driver"+driver);
        logger.info("=====CHROME=====");
        break;
      case "edge":
        driver = new EdgeDriver();
        logger.info("=====EDGE=====");
        break;
      case "firefox":
        driver = new FirefoxDriver();
        //        logger.info("=====FIREFOX=====");
        break;
      default:
        //        logger.info("=====Invalid Browser Name..=====");
        System.out.println("Invalid Browser Name..");
        return null;
      }
    }
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    System.out.println("WebDriver :: "+driver);

    return driver;
  }

  public static Logger getLogger() {
    logger = LogManager.getLogger(); // org.apache.logging.log4j.LogManager;
    return logger;
  }

  public static WebDriver getDriver() {
    System.out.println("WebDriver :: "+driver);

    return driver;
  }
}