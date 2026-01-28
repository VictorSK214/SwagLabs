package data;

import org.testng.annotations.DataProvider;

public class UserDataProvider {

  private static final String PASSWORD = "secret_sauce";

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

  @DataProvider(name = "PerformanceGlitchUser")
  public static Object[][] performanceGlitchUser() {
    return new Object[][]{{
        User.builder()
            .login("performance_glitch_user")
            .password(PASSWORD)
            .build()
    }};
  }

  @DataProvider(name = "UserWithIncorrectPassword")
  public static Object[][] userWithIncorrectPassword() {
    return new Object[][]{{
        User.builder()
            .login("standard_user")
            .password("incorrect_password")
            .build()
    }};
  }
}