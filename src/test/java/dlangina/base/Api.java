package dlangina.base;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.openqa.selenium.Cookie;

public class Api {

  public static void setUpRestAssured() {
    RestAssured.baseURI = "http://demowebshop.tricentis.com";
    RestAssured.basePath = "/";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @Step("Открытие браузера и авторизация пользователя по API")
  public void openBrowserAndLoginByAPI(String email, String pass) {
    String authCookie = loginAndGetCookies(email, pass);
    open("");
    addAuthCookieToBrowser(authCookie);
    open("");
    remainingLogin(email);
  }

  @Step("Добавление товара в Wishlist по API")
  public void addProductToWishListByApi(String email, String pass, String product) {
    String authCookie = loginAndGetCookies(email, pass);
    addProductToWishList(authCookie, product);
  }


  private String loginAndGetCookies(String email, String pass) {
    return given()
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .formParam("Email", email)
        .formParam("Password", pass)
        .when()
        .post("/login")
        .then()
        .statusCode(302)
        .extract().cookie("NOPCOMMERCE.AUTH");
  }

  public void addProductToWishList(String cookie, String product) {
    given()
        .cookie("NOPCOMMERCE.AUTH", cookie)
        .contentType("application/json; charset=utf-8")
        .when()
        .post(product)
        .then()
        .statusCode(200)
        .body("message",
              is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"));
  }

  private void addAuthCookieToBrowser(String authCookie) {
    getWebDriver().manage().addCookie(
        new Cookie("NOPCOMMERCE.AUTH", authCookie));
  }

  @Step("Проверка успешности авторизации")
  void remainingLogin(String email) {
    $(".account").shouldHave(exactText(email));
  }
}
