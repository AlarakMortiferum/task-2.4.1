package ru.netology.testmode.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;  // сюда входит text()
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private final ElementsCollection cards = $$(".list__item"); // все карточки

    public int getCardBalance(String cardNumber) {
        SelenideElement card = cards.findBy(text(cardNumber));
        String text = card.$(".list__item__balance").getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String digits = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(digits);
    }

    public TransferPage selectCardToTransfer(String cardNumber) {
        SelenideElement card = cards.findBy(text(cardNumber));
        card.$("button.button").click();
        return new TransferPage();
    }
}
