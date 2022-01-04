package dlangina.base;

import static dlangina.base.Api.setUpRestAssured;

import com.codeborne.selenide.Configuration;
import dlangina.pages.WishlistPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

  public Navigation navigation = new Navigation();
  public Api api = new Api();
  public WishlistPage wishlistPage = new WishlistPage();

  @BeforeAll
  static void setUp() {
    Configuration.baseUrl = "http://demowebshop.tricentis.com";
    setUpRestAssured();
  }
}
