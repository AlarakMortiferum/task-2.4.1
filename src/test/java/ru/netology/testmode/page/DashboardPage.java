package ru.netology.testmode.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;  // сюда входит text()
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private final ElementsCollection cards = $$(".list__item"); // все карточки

    public int getCardBalance(String cardNumber) {
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        String cardPartialText = "**** **** **** " + lastFourDigits;
        SelenideElement card = cards.findBy(text(cardPartialText));
        String cardText = card.getText();
        return extractBalance(cardText);
    }

    private int extractBalance(String text) {
        String balancePart = text.substring(text.indexOf("баланс:") + 7, text.indexOf("р."));
        return Integer.parseInt(balancePart.trim());
    }


    public TransferPage selectCardToTransfer(String cardNumber) {
        String lastFourDigits = cardNumber.substring(cardNumber.length() - 4);
        String cardPartialText = "**** **** **** " + lastFourDigits;

        SelenideElement card = cards.findBy(text(cardPartialText));
        card.$("button.button").click();

        return new TransferPage();
    }
}
