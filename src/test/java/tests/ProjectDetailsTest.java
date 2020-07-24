package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class ProjectDetailsTest extends BaseTest {

    @Test
    @Description("Adding component to the project and then changing component name")
    public void addComponentTest() {
        loginPage
                .openPage()
                .isPageOpened()
                .login("kristina@mailinator.com", "password123");
        projectsPage
                .openPage()
                .clickOnProjectCreated(3);
        projectDetailsPage
                .clickAddNewComponent()
                .addComponentToProject(1, "Hoff")
                .clickCreateButton()
                .clickUpdateButton()
                .verifyNumberOfComponents(projectDetailsPage.getNumberOfAddedComponents())
                .getComponent(projectDetailsPage.getNumberOfAddedComponents())
                .verifyComponentFields("Video Chat", "Hoff");
        projectsPage
                .openPage()
                .clickOnProjectCreated(3);
        projectDetailsPage
                .verifyNumberOfComponents(projectDetailsPage.getNumberOfAddedComponents())
                .getComponent(projectDetailsPage.getNumberOfAddedComponents())
                .editComponent("AQAQ")
                .verifyComponentFields("Video Chat", "AQAQ");
    }
}

