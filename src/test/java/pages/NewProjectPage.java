package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import static org.testng.AssertJUnit.assertEquals;

public class NewProjectPage extends BasePage {

    private static final String NEW_PROJECT_URL = "https://dev.integrivideo.com/app/projects/new";
    private static final By UPDATE_BUTTON = By.xpath("//div//button[text()='Update']");
    private static final By DELETE_DOMAIN_LOCATOR = By.xpath("//*[@class='input-group-append remove-domain']/span");
    private static final By EDIT_BUTTON = By.xpath("//a[text()='Edit']");
    private static final By PROJECT_NAME_INPUT = By.cssSelector("[name='name']");
    private static final By DESCRIPTION_INPUT = By.cssSelector("[placeholder='Type here...']");
    private static final By DOMAINS_INPUT = By.xpath("//input[@name='domains[]'][1]");
    private static final By CREATE_BUTTON = By.xpath("//div//button[text()='Create']");

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewProjectPage openPage() {
        driver.get(NEW_PROJECT_URL);
        return this;
    }

    @Override
    public NewProjectPage isPageOpened() {
        waitForElementVisibility(By.id("navbarMenu"));
        return this;
    }

    public NewProjectPage addNewProject(String projectName, String description, String domains) {
        driver.findElement(PROJECT_NAME_INPUT).click();
        driver.findElement(PROJECT_NAME_INPUT).clear();
        driver.findElement(PROJECT_NAME_INPUT).sendKeys(projectName);
        driver.findElement(DESCRIPTION_INPUT).click();
        driver.findElement(DESCRIPTION_INPUT).clear();
        driver.findElement(DESCRIPTION_INPUT).sendKeys(description);
        driver.findElement(DOMAINS_INPUT).click();
        driver.findElement(DOMAINS_INPUT).clear();
        driver.findElement(DOMAINS_INPUT).sendKeys(domains);
        return this;
    }

    public NewProjectPage clickCreateButton() {
        driver.findElement(CREATE_BUTTON).click();
        return this;
    }

    public NewProjectPage addSecondDomain(String domain){
        driver.findElements(DOMAINS_INPUT).get(1).click();
        driver.findElements(DOMAINS_INPUT).get(1).clear();
        driver.findElements(DOMAINS_INPUT).get(1).sendKeys(domain);
        return this;
    }

    public NewProjectPage verifyProjectInputsFilled(String name, String domain) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(PROJECT_NAME_INPUT)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(DOMAINS_INPUT)));
        assertEquals(driver.findElement(PROJECT_NAME_INPUT).getAttribute("value"), name);
        assertEquals(driver.findElement(DOMAINS_INPUT).getAttribute("value"), domain);
        return this;
    }

    public NewProjectPage verifySecondDomainFilled(String domain) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElements(DOMAINS_INPUT).get(1)));
        assertEquals(driver.findElements(DOMAINS_INPUT).get(1).getAttribute("value"), domain);
        return this;
    }

    public NewProjectPage verifyTotalNumberDomains(int expectedNumberDomains) {
        int actualNumberOfDomainFields = driver.findElements(DELETE_DOMAIN_LOCATOR).size();
        assertEquals(actualNumberOfDomainFields,expectedNumberDomains);
        return this;
    }

    public NewProjectPage removeDomain(int number) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElements(DELETE_DOMAIN_LOCATOR).get(number - 1)));
        driver.findElements(DELETE_DOMAIN_LOCATOR).get(1).click();
        return this;
    }

    public NewProjectPage clickEditButton() {
        driver.findElement(EDIT_BUTTON).click();
        return this;
    }

    public NewProjectPage clickUpdateButton() {
        driver.findElement(UPDATE_BUTTON).click();
        return this;
    }
}


