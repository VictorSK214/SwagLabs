package ui;

import base.BaseTest;
import pages.LoginPage;
import data.User;
import data.UserDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

@Feature("Авторизация")
public class LoginTest extends BaseTest {

  @Test(
      description = "TC1. Успешный логин (standard_user / secret_sauce)",
      dataProvider = "StandardUser", dataProviderClass = UserDataProvider.class,
      groups = {"ui"})
  @Description("При вводе валидных учетных данных пользователь успешно авторизуется")
  @Owner("Victor.SK")
  public void test1(User user) {
    new LoginPage()
        .fillUserName(user.getLogin())
        .fillPassword(user.getPassword())
        .clickLoginButton()
        .checkProductsHeader();
  }

  @Test(
      description = "TC2. Логин с неверным паролем",
      dataProvider = "UserWithIncorrectPassword", dataProviderClass = UserDataProvider.class,
      groups = {"ui"})
  @Description("При вводе валидного логина и неверного пароля отображается сообщение об ошибке: " +
      "'Epic sadface: Username and password do not match any user in this service'")
  @Owner("Victor.SK")
  public void test2(User user) {
    new LoginPage()
        .fillUserName(user.getLogin())
        .fillPassword(user.getPassword())
        .submitInvalidLogin("Epic sadface: Username and password do not match any user in this service");
  }

  @Test(
      description = "TC3. Логин заблокированного пользователя (locked_out_user)",
      dataProvider = "LockedOutUser", dataProviderClass = UserDataProvider.class,
      groups = {"ui"})
  @Description("При попытке входа под заблокированным пользователем отображается сообщение: " +
      "'Epic sadface: Sorry, this user has been locked out'")
  @Owner("Victor.SK")
  public void test3(User user) {
    new LoginPage()
        .fillUserName(user.getLogin())
        .fillPassword(user.getPassword())
        .submitInvalidLogin("Epic sadface: Sorry, this user has been locked out.");
  }

  @Test(
      description = "TC4. Логин c пустыми полями", groups = {"ui"})
  @Description("При попытке входа с пустыми полями отображается сообщение: 'Username is required'")
  @Owner("Victor.SK")
  public void test4() {
    new LoginPage()
        .checkUsernameEmpty()
        .checkPasswordEmpty()
        .submitInvalidLogin("Epic sadface: Username is required");
  }

  @Test(
      description = "TC5. Логин пользователем performance_glitch_user (проверить корректный переход и что страница " +
          "открывается несмотря на возможные задержки)",
      dataProvider = "PerformanceGlitchUser", dataProviderClass = UserDataProvider.class,
      groups = {"ui"})
  @Description("Пользователь с возможными задержками производительности успешно авторизуется")
  @Owner("Victor.SK")
  public void test5(User user) {
    new LoginPage()
        .fillUserName(user.getLogin())
        .fillPassword(user.getPassword())
        .clickLoginButton()
        .checkProductsHeader();
  }
}