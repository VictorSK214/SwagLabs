package pages;

import base.BasePage;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends BasePage {

  private By userNameTextInput = By.xpath("//input[@id='user-name']");
  private By passwordTextInput = By.xpath("//input[@id='password']");
  private By loginButton = By.xpath("//input[@id='login-button']");
  private By errorTextField = By.xpath("//h3[@data-test='error']");

  @Step("Ввести в поле 'Username' имя пользователя")
  public LoginPage fillUserName(String userName) {
    fillTextField(userNameTextInput, userName);
    return this;
  }

  @Step("Ввести в поле 'Password' пароль")
  public LoginPage fillPassword(String password) {
    fillTextField(passwordTextInput, password);
    return this;
  }

  @Step("Клик на кнопку 'Login'")
  public MainPage clickLoginButton() {
    waitAndClick(loginButton);
    return new MainPage();
  }

  @Step("Проверить, что при попытке входа в приложение с некорректными данными, отображается ошибка: '{error}'")
  public LoginPage submitInvalidLogin(String error) {
    waitAndClick(loginButton);
    assertThat(waitVisible(errorTextField).getText()).isEqualTo(error);
    return this;
  }

  @Step("Проверить, что поле 'Username' не заполнено")
  public LoginPage checkUsernameEmpty() {
    assertThat(waitVisible(userNameTextInput).getAttribute("value")).isEmpty();
    return this;
  }

  @Step("Проверить, что поле 'Password' не заполнено")
  public LoginPage checkPasswordEmpty() {
    assertThat(waitVisible(userNameTextInput).getAttribute("value")).isEmpty();
    return this;
  }
}