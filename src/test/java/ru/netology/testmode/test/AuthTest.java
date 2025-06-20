package ru.netology.testmode.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.testmode.data.DataHelper;
import ru.netology.testmode.page.LoginPage;
import ru.netology.testmode.page.TransferPage;
import ru.netology.testmode.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var authInfo = DataHelper.getAuthInfo();  // vasya / password123
        var verificationCode = DataHelper.getVerificationCode();

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.verify(verificationCode);


        String firstCard = DataHelper.getFirstCardNumber();
        String secondCard = DataHelper.getSecondCardNumber();

        int firstCardBalanceBefore = dashboardPage.getCardBalance(firstCard);
        int secondCardBalanceBefore = dashboardPage.getCardBalance(secondCard);

        int amount = firstCardBalanceBefore / 4; // Не жёстко, надёжнее

        TransferPage transferPage = dashboardPage.selectCardToTransfer(secondCard);
        dashboardPage = transferPage.transfer(firstCard, amount);

        int firstCardBalanceAfter = dashboardPage.getCardBalance(firstCard);
        int secondCardBalanceAfter = dashboardPage.getCardBalance(secondCard);

        assertEquals(firstCardBalanceBefore - amount, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore + amount, secondCardBalanceAfter);
    }
}
