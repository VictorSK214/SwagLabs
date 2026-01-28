package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static base.BaseTest.getDriver;

public class BasePage {

  private WebDriverWait wait;
  private final Duration DEFAULT_TIMEOUT_SECONDS = Duration.ofSeconds(10);

  public BasePage() {
    this.wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT_SECONDS);
  }

  /** Ожидание видимости элемента */
  protected WebElement waitVisible(By locator) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /** Ожидание кликабельности элемента и клик */
  protected void waitAndClick(By locator) {
    wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
  }

  /** Заполнить текстовое поле */
  protected void fillTextField(By locator, String text) {
    waitVisible(locator).sendKeys(text);
  }
}