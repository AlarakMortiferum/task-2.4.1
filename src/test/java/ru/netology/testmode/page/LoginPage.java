package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.testmode.data.DataHelper.AuthInfo;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");

    public LoginPage openPage() {
        open("/login");
        return this;
    }

    public VerificationPage validLogin(AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage shouldSeeError(String message) {
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(com.codeborne.selenide.Condition.text(message));
        return this;
    }
}
