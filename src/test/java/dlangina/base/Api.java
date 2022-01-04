package dlangina.base;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

public class Api {

  public static Cookies authCookies;
  String getWishListItemCounter;
  Response wishListItemsQuantity;

  public static void setUpRestAssured() {
    RestAssured.baseURI = "http://demowebshop.tricentis.com";
    RestAssured.basePath = "/";
//    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  public void openBrowserAndLoginByAPI(String email, String pass) {
    loginAndGetCookies(email, pass);
    open("");
    addAuthCookieToBrowser("NOPCOMMERCE.AUTH");
    open("");
    remainingLogin(email);
  }

  @Step("Авторизация пользователя")
  private Cookies loginAndGetCookies(String email, String pass) {
    return authCookies =
        given()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .formParam("Email", email)
            .formParam("Password", pass)
            .when()
            .post("/login")
            .then()
            .statusCode(302)
            .extract().response().getDetailedCookies();
  }

  public Response addToWishListWithResponse(Cookies cookies, String product) {
    return
        given()
            .cookies(cookies)
            .when()
            .post(product)
            .then()
            .statusCode(200)
            .extract().response();
  }

  public static void addAuthCookieToBrowser(String cookieName) {
    getWebDriver().manage().addCookie(
        new Cookie(cookieName, authCookies.getValue(cookieName)));
  }

  @Step("Проверка успешности авторизации")
  void remainingLogin(String email) {
    $(".account").shouldHave(exactText(email));
  }

//  wishListItemsQuantity =
//
//  addToWishListWithResponse(authCookies, "addproducttocart/details/14/2");
//
//  getWishListItemCounter =wishListItemsQuantity.path("updatetopwishlistsectionhtml");
}
