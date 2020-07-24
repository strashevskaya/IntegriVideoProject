package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.AssertJUnit.assertEquals;

public class ProjectsPage extends BasePage {

    private static final String PROJECTS_PAGE_URL = "https://dev.integrivideo.com/app/projects";
    private static final By CREATE_PROJECT_BUTTON = By.cssSelector("//button[@class='btn'");
    private static final By ADD_PROJECT_LINK = By.xpath("//*[@href='/app/projects/new']");
    private static final By ADDED_PROJECT = By.xpath("//div[@class='project']");
    private static final By PROJECT_LOCATOR = By.xpath("//span[contains(text(),'active')]");

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProjectsPage openPage() {
        driver.get(PROJECTS_PAGE_URL);
        return this;
    }

    @Override
    public ProjectsPage isPageOpened() {
        waitForElementVisibility(By.cssSelector(".project.new"));
        return this;
    }

    public void clickAddProjectButton() {
        driver.findElement(ADD_PROJECT_LINK).click();
    }

    public ProjectsPage clickOnProjectCreated(int projectNumber) {
        driver.findElements(ADDED_PROJECT).get(projectNumber - 1).click();
        return this;
    }

    public int getNumberOfAddedProjects() {
        int numberOfAddedProjects = driver.findElements(ADDED_PROJECT).size();
        return numberOfAddedProjects;
    }

    public ProjectsPage verifyNumberOfCreatedProjects(int expectedNumber) {
        int actualNumber = driver.findElements(ADDED_PROJECT).size();
        assertEquals(actualNumber, expectedNumber);
        return this;
    }

}