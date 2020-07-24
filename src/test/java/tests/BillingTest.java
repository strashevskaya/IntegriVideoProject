package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class BillingTest extends BaseTest {

    @Test
    @Description("Add and remove card in payment system")
    public void addRemoveCardTest() {
        String cardNumber = "4242424242424242";
        String cardMonthExpiry = "02";
        String cardYearExpiry = "2022";
        String cardName = "user name";

        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "password123");
        billingPage
                .openPage()
                .isPageOpened()
                .clickAddNewCard()
                .addCardFields(cardNumber,cardMonthExpiry,cardYearExpiry, cardName)
                .clickAddCard()
                .verifyAddedCard(billingPage.getCurrentNumberOfAddedCards())
                .removeCard(billingPage.getCurrentNumberOfAddedCards())
                .verifyCardRemoved("Payment method successfully removed")
                .verifyAddedCard(billingPage.getCurrentNumberOfAddedCards());
    }
}
