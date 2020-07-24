package tests;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class NewProjectTest extends BaseTest {

    @Test
    @Description("Creating new project")
    public void createProject() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "password123");
        projectsPage
                .openPage()
                .isPageOpened()
                .clickAddProjectButton();
        newProjectPage
                .openPage()
                .isPageOpened()
                .addNewProject("TMS", "testing", "tms.com")
                .clickCreateButton();
        projectsPage.verifyNumberOfCreatedProjects(projectsPage.getNumberOfAddedProjects());
    }

    @Test
    @Description("Improving fields in created project")
    public void editProjectTest() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "password123");
        projectsPage
                .openPage()
                .isPageOpened()
                .clickOnProjectCreated(4);
        newProjectPage
                .clickEditButton()
                .addNewProject("RTSD", "patterns", "btms.com")
                .clickEditButton()
                .verifyProjectInputsFilled("RTSD", "btms.com");
    }

    @Test
    @Description("Add second domain and remove one")
    public void addSecondDomainTest() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "password123");
        projectsPage
                .openPage()
                .isPageOpened()
                .clickAddProjectButton();
        newProjectPage
                .openPage()
                .isPageOpened()
                .addNewProject("TMS", "testing", "tms.com")
                .addSecondDomain("var.com")
                .clickCreateButton();
        projectsPage.clickOnProjectCreated(projectsPage.getNumberOfAddedProjects());
        newProjectPage
                .clickEditButton()
                .verifySecondDomainFilled("var.com")
                .removeDomain(2)
                .clickUpdateButton();
        projectsPage.clickOnProjectCreated(projectsPage.getNumberOfAddedProjects());
        newProjectPage
                .clickEditButton()
                .verifyTotalNumberDomains(1);
    }
}
