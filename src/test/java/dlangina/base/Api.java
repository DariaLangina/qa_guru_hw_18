package dlangina.base;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

public class Api {

  String getWishListItemCounter;
  Response wishListItemsQuantity;

  public static void setUpRestAssured() {
    RestAssured.baseURI = "http://demowebshop.tricentis.com";
    RestAssured.basePath = "/";
//    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  public void openBrowserAndLoginByAPI(String email, String pass) {
    String cookie = getCookie(email, pass);
    open("");
    addAuthCookieToBrowser(cookie);
    open("");
    remainingLogin(email);
  }

  @Step("Авторизация пользователя")
  private String getCookie(String email, String pass) {
    return given()
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .formParam("Email", email)
        .formParam("Password", pass)
        .when()
        .post("/login")
        .then()
        .statusCode(302)
        .extract()
        .cookie("NOPCOMMERCE.AUTH");
  }

  public Response addToWishListWithResponse(String email, String pass, String product) {
    return
        given()
            .cookie(getCookie(email, pass))
            .when()
            .post(product)
            .then()
            .statusCode(200)
            .extract().response();
  }

  void addAuthCookieToBrowser(String cookie) {
    getWebDriver().manage().addCookie(
        new Cookie("NOPCOMMERCE.AUTH", cookie));
  }

  @Step("Проверка успешности авторизации")
  void remainingLogin(String email) {
    $(".account").shouldHave(exactText(email));
  }

//  void testTest() {
//    wishListItemsQuantity =
//
//        addToWishListWithResponse(authCookies, "addproducttocart/details/14/2");
//
//    getWishListItemCounter = wishListItemsQuantity.path("updatetopwishlistsectionhtml");
//  }

}
