package dlangina.base;

import static com.codeborne.selenide.Selenide.open;
import static dlangina.fragments.Pages.checkTitle;

import io.qameta.allure.Step;

public class Navigation {

  @Step("Открытие страницы Wishlist")
  public void openWishlistPage() {
    open("/wishlist");
    checkTitle("Wishlist");
  }
}
