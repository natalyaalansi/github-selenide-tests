package github;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideSoftAssertionsSearch {
    @Test
    void shouldFindJunitExample() {
        open("https://github.com/");
        $("[data-target$='inputButtonText']").click();
        $("#query-builder-test").setValue("Selenide").pressEnter();
        $("[data-testid='results-list']").$("div", 0).$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("[class$='wiki-more-pages-link']").$("button").click();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("#user-content-3-using-junit5-extend-test-class").parent().shouldHave(text("JUnit5"));
        $("#user-content-3-using-junit5-extend-test-class").parent().sibling(0).shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");

                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));
    }
}
