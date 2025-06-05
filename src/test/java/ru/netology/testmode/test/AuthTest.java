package ru.netology.testmode.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.netology.testmode.data.DataGenerator;
import ru.netology.testmode.page.DashboardPage;
import ru.netology.testmode.page.LoginPage;
import ru.netology.testmode.page.TransferPage;
import ru.netology.testmode.page.VerificationPage;
import java.util.Map;
import java.util.HashMap;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var authInfo = DataGenerator.getAuthInfo();  // vasya/qwerty123
        var verificationCode = DataGenerator.getVerificationCode();

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        String firstCard = "5559 0000 0000 0001";
        String secondCard = "5559 0000 0000 0002";

        int amount = 1000;

        // Балансы до перевода
        int firstCardBalanceBefore = dashboardPage.getCardBalance(firstCard);
        int secondCardBalanceBefore = dashboardPage.getCardBalance(secondCard);

        // Переходим на страницу перевода с пополнением второй карты
        TransferPage transferPage = dashboardPage.selectCardToTransfer(secondCard);
        dashboardPage = transferPage.transfer(firstCard, amount);

        // Балансы после перевода
        int firstCardBalanceAfter = dashboardPage.getCardBalance(firstCard);
        int secondCardBalanceAfter = dashboardPage.getCardBalance(secondCard);

        // Проверяем, что деньги списались и пришли
        assertEquals(firstCardBalanceBefore - amount, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore + amount, secondCardBalanceAfter);
    }
}
