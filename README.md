<div align="center">
    <h1>Проект по автоматизации тестирования web-приложения</h1>
</div>

<div align="center">
    <a href="https://www.saucedemo.com/">
        <img src="png/SwagLabs.png" alt="Swag Labs" style="display: block; margin: 0 auto;"/>
    </a>
</div>

<div align="center">

<h1 align="center"> Технологический стек</h1>

[![Windows](https://skillicons.dev/icons?i=windows)](https://www.microsoft.com/ru-ru/download/windows/)
[![Idea](https://skillicons.dev/icons?i=idea)](https://www.jetbrains.com/)
[![Java](https://skillicons.dev/icons?i=java)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Maven](https://skillicons.dev/icons?i=maven)](https://maven.apache.org/)
[![Selenium](https://skillicons.dev/icons?i=selenium)](https://www.selenium.dev/)
[![Git](https://skillicons.dev/icons?i=git)](https://git-scm.com/)

[![Allure](https://img.shields.io/badge/Allure-Report-blue?style=flat&logo=akaunting&logoColor=16b67b)](https://docs.qameta.io/allure/)
[![TestNG](https://img.shields.io/badge/TestNG-Framework-blue)](https://testng.org/)

</div>

---

Тестирование пользовательского интерфейса реализовано с применением 
паттерна [PageObject](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/).  

```java
  private By userNameTextInput = By.xpath("//input[@id='user-name']");
```

```java
  @Step("Ввести в поле 'Username' имя пользователя")
  public LoginPage fillUserName(String userName) {
    fillTextField(userNameTextInput, userName);
    return this;
  }
```

Методы с аннотацией [@DataProvider](https://testng.org/#_parameters_with_dataproviders),
используются для предоставления параметров тестовому методу.

```java
  @DataProvider(name = "StandardUser")
  public static Object[][] standardUser() {
    return new Object[][]{{
        User.builder()
            .login("standard_user")
            .password(PASSWORD)
            .build()
    }};
  }

  @DataProvider(name = "LockedOutUser")
  public static Object[][] lockedOutUser() {
    return new Object[][]{{
        User.builder()
            .login("locked_out_user")
            .password(PASSWORD)
            .build()
    }};
  }
```

```java
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
```

---

Запуск тестов из терминала и формирование отчета: `mvn clean test -P ui allure:report allure:serve`.

<div style="text-align: right;">
    <img src="png/Allure.png" alt="Allure" style="display: inline-block;"/>
</div>