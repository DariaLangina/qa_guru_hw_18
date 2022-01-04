package dlangina.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class WishlistPage {

  final private SelenideElement
      container = $(".wishlist-content"),
      sharingUrl = $(".share-label"),
      shareLink = $(".share-link");


  public WishlistPage checkProductName(String productName) {
    container.shouldHave(text(productName));
    return this;
  }

  public void checkSharing() {
    sharingUrl.shouldBe(visible);
    shareLink.shouldBe(visible);
  }

}
