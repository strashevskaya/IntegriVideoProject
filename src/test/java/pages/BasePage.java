package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public abstract class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
    }

    public abstract BasePage openPage();

    public abstract BasePage isPageOpened();

    public void waitForElementVisibility(By elementBy) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
        } catch (TimeoutException ex) {
            Assert.fail("can't find element. dropped by timeout");
            ex.printStackTrace();
        }
    }

}
