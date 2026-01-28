package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage extends BasePage {

  private By productsHeader = By.xpath("//span[text()='Products']");

  @Step("Проверить, что отображается заголовок 'Products'")
  public MainPage checkProductsHeader() {
    assertThat(waitVisible(productsHeader).getText()).isEqualTo("Products");
    return this;
  }
}