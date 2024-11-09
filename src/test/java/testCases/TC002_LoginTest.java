package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import factory.DriverFactory;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
  static Logger logger;

  @Test(groups={"sanity","master"})
  public void verify_login() {
    logger = DriverFactory.getLogger();
    logger.info("****** Starting TC002_LoginTest *******");
    try {
      // HomePage
      HomePage hp = new HomePage(driver);
      hp.clickMyAccount();
      hp.clickLogin();
      logger.info("****** HomePage *******");

      // LoginPage
      LoginPage lp = new LoginPage(driver);
      lp.setEmail(prop.getProperty("email"));
      lp.setPassword(prop.getProperty("password"));
      lp.clickLogin();
      logger.info("****** LoginPage *******");

      // MyAccountPage
      MyAccountPage map = new MyAccountPage(driver);
      boolean targetPage = map.isMyAccountPageExists();
      logger.info("****** MyAccountPage *******");

      Assert.assertEquals(targetPage, true, "Login Failed");
      logger.info("****** Assert *******");
      // Assert.assertTrue(targetPage);
    } catch (Exception e) {
      logger.info("****** Catch *******");
      Assert.fail();
    }
    logger.info("****** Finished TC002_LoginTest *******");
  }
}
