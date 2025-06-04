package ru.netology.testmode.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amountInput = $("[data-test-id=amount] input");
    private final SelenideElement fromCardInput = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("button.button");

    public DashboardPage transfer(String fromCardNumber, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromCardInput.setValue(fromCardNumber);
        transferButton.click();
        return new DashboardPage();
    }
}
