package dlangina.fragments;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class Pages {

  public static SelenideElement title = $(".page-title h1");

  public static void checkTitle(String name) {
    title.shouldHave(exactText(name));
  }
}
