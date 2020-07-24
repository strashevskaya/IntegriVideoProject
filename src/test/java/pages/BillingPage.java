package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.AssertJUnit.assertEquals;

public class BillingPage extends BasePage {

    String BILLING_URL = "https://dev.integrivideo.com/app/billing";
    private static final By ADD_NEW_CARD_LINK = By.xpath("//a[@href='/app/billing/payment-methods/new']");
    private static final By CARD_NUMBER_INPUT = By.xpath("//*[@name='number']");
    private static final By CARD_MONTH_FIELD = By.xpath("//*[@name='expirationMonth']");
    private static final By CARD_YEAR_FIELD = By.xpath("//*[@name='expirationYear']");
    private static final By CARD_HOLDER_NAME_INPUT = By.xpath("//*[@name='cardholderName']");
    private static final By ADD_CARD_BUTTON = By.xpath("//button[@class='btn']");
    private static final By MESSAGE_NOTIFICATION = By.xpath("//span[@data-notify='message']");
    private static final By PAYPAL_LINK = By.xpath("//*[@class='zoid-component-frame zoid-visible']");
    private static final By ADDED_CARD = By.xpath("//*[@class='cards']/div[@class='row']");
    private static final By REMOVE_CARD_LINK = By.xpath("//*[@class='cards']//a[text()='Remove']");


    public BillingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BillingPage openPage() {
        driver.get(BILLING_URL);
        return this;
    }

    @Override
    public BillingPage isPageOpened() {
        waitForElementVisibility(By.xpath("//button[@class='btn']"));
        return this;
    }

    public BillingPage clickAddNewCard() {
        driver.findElement(ADD_NEW_CARD_LINK).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(PAYPAL_LINK)));
        return this;
    }

    public BillingPage addCardFields(String cardNumber, String month, String year, String name) {
        driver.findElement(CARD_NUMBER_INPUT).click();
        driver.findElement(CARD_NUMBER_INPUT).sendKeys(cardNumber);
        driver.findElement(CARD_MONTH_FIELD).click();
        driver.findElement(CARD_MONTH_FIELD).sendKeys(month);
        driver.findElement(CARD_YEAR_FIELD).click();
        driver.findElement(CARD_YEAR_FIELD).sendKeys(year);
        driver.findElement(CARD_HOLDER_NAME_INPUT).click();
        driver.findElement(CARD_HOLDER_NAME_INPUT).sendKeys(name);
        return this;
    }

    public BillingPage clickAddCard() {
        driver.findElement(ADD_CARD_BUTTON).click();
        return this;
    }

    public BillingPage verifyAddedCard(int expectedCountOfCards) {
        int actualCountOfCards = driver.findElements(ADDED_CARD).size();
        assertEquals(actualCountOfCards, expectedCountOfCards);
        return this;
    }

    public BillingPage removeCard(int number) {
        driver.findElements(REMOVE_CARD_LINK).get(number - 1).click();
        return this;
    }

    public int getCurrentNumberOfAddedCards() {
        int numberOfAddedCards = driver.findElements(ADDED_CARD).size();
        return numberOfAddedCards;
    }

    public BillingPage verifyCardRemoved(String expectedText) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MESSAGE_NOTIFICATION)));
        String text = driver.findElement(MESSAGE_NOTIFICATION).getText();
        assertEquals(text, expectedText);
        return this;
    }

}