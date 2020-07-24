package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.testng.AssertJUnit.assertEquals;

public class ProjectDetailsPage extends BasePage {

    String PROJECTS_URL = "https://dev.integrivideo.com/app/projects";
    private static final By ADD_NEW_COMPONENT_BUTTON = By.xpath("//div[@class='status']");
    private static final By EDIT_BUTTON = By.xpath("//a[text()='Edit']");
    private static final By COMPONENT_TYPE_SELECT = By.xpath("//option");
    private static final By COMPONENT_NAME_INPUT = By.xpath("//input[@placeholder='New component']");
    private static final By CREATE_BUTTON = By.xpath("//div//button[text()='Create']");
    private static final By CREATED_COMPONENT = By.xpath("//div[@class='component']");
    private static final By COMPONENT_TYPE = By.xpath("//input[@name='type']");
    private static final By UPDATE_BUTTON = By.xpath("//div//button[text()='Update']");

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectDetailsPage openPage() {
        driver.get(PROJECTS_URL);
        return this;
    }

    @Override
    public ProjectDetailsPage isPageOpened() {
        return this;
    }

    public ProjectDetailsPage clickAddNewComponent() {
        driver.findElement(ADD_NEW_COMPONENT_BUTTON).click();
        return this;
    }

    public ProjectDetailsPage addComponentToProject(int option, String name) {
        driver.findElements(COMPONENT_TYPE_SELECT).get(option - 1).click();
        driver.findElement(COMPONENT_NAME_INPUT).click();
        driver.findElement(COMPONENT_NAME_INPUT).clear();
        driver.findElement(COMPONENT_NAME_INPUT).sendKeys(name);
        return this;
    }

    public ProjectDetailsPage getComponent(int componentNumber) {
        driver.findElements(CREATED_COMPONENT).get(componentNumber - 1).click();
        return this;
    }

    public ProjectDetailsPage clickCreateButton() {
        driver.findElement(CREATE_BUTTON).click();
        return this;
    }

    public ProjectDetailsPage clickUpdateButton() {
        driver.findElement(UPDATE_BUTTON).click();
        return this;
    }

    public int getNumberOfAddedComponents() {
        int numberOfAddedComponents = driver.findElements(CREATED_COMPONENT).size();
        return numberOfAddedComponents;
    }

    public ProjectDetailsPage verifyNumberOfComponents(int expectedNumberOfComponents) {
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(CREATED_COMPONENT)));
        assertEquals(driver.findElements(CREATED_COMPONENT).size(), expectedNumberOfComponents);
        return this;
    }

    public ProjectDetailsPage verifyComponentFields(String expectedTypeComponent, String expectedNameComponent) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(COMPONENT_TYPE)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(COMPONENT_NAME_INPUT)));
        assertEquals(driver.findElement(COMPONENT_TYPE).getAttribute("value"), expectedTypeComponent);
        assertEquals(driver.findElement(COMPONENT_NAME_INPUT).getAttribute("value"), expectedNameComponent);
        return this;
    }

    public ProjectDetailsPage editComponent( String editedName) {
        driver.findElement(COMPONENT_NAME_INPUT).click();
        driver.findElement(COMPONENT_NAME_INPUT).clear();
        driver.findElement(COMPONENT_NAME_INPUT).sendKeys(editedName);
        driver.findElement(UPDATE_BUTTON).click();
        return this;
    }


}
