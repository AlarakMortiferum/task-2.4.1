package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public DashboardPage verify(String code) {
        codeField.setValue(code);
        verifyButton.click();
        return new DashboardPage();
    }

    public VerificationPage shouldSeeError(String message) {
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(com.codeborne.selenide.Condition.text(message));
        return this;
    }
}
