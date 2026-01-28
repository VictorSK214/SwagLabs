package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static base.WebDriverFactory.Browser.CHROME;
import static base.WebDriverFactory.Browser.FIREFOX;
import static base.WebDriverFactory.getWebDriver;

public class BaseTest {

  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  public static WebDriver getDriver() {
    return driver.get();
  }

  private final String BASE_URL = "https://www.saucedemo.com";

  @BeforeMethod(alwaysRun = true)
  public void createDriver() {
    driver.set(getWebDriver(CHROME));
    getDriver().manage().window().maximize();
    getDriver().get(BASE_URL);
  }

  @AfterMethod(alwaysRun = true)
  public void quitDriver() {
    if (getDriver() != null) {
      getDriver().quit();
    }
  }
}