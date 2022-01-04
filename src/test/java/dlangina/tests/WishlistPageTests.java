package dlangina.tests;

import static dlangina.base.Api.authCookies;

import dlangina.base.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WishlistPageTests extends TestBase {

  @BeforeEach
  void ensurePrecondition() {
    api.openBrowserAndLoginByAPI("dlangina@qa.guru", "dlangina@qa.guru1");
    api.addToWishListWithResponse(authCookies, "addproducttocart/details/14/2");
    navigation.openWishlistPage();
  }

  @AfterEach
  void ensurePostcondition() {
    //TODO удалять добавленный в Wishlist товар по API
  }

  @Test
  @DisplayName("Проверка оторажения товара на странице Wishlist")
  void addToWishList() {
    wishlistPage.checkProductName("Black & White Diamond Heart");
  }


}