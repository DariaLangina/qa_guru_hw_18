package dlangina.tests;

import dlangina.base.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class WishlistPageTests extends TestBase {

  @BeforeEach
  void ensurePrecondition() {
    String email = "dlangina@qa.guru";
    String pass = "dlangina@qa.guru1";
    String product = "addproducttocart/details/14/2";

    String cookie = api.getCookieByApi(email, pass);
    api.openBrowserAndLoginByAPI(cookie, email);
    api.addProductToWishListByApi(cookie, product);
    navigation.openWishlistPage();
  }

  @AfterEach
  void ensurePostcondition() {
    //TODO удалять добавленный в Wishlist товар по API
  }

  @Test
  @Tag("UI")
  @DisplayName("Проверка оторажения товара на странице Wishlist")
  void addToWishList() {
    wishlistPage.checkProductName("Black & White Diamond Heart");
  }
}