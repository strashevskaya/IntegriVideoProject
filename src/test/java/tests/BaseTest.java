package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    LoginPage loginPage;
    ProjectsPage projectsPage;
    NewProjectPage newProjectPage;
    ProjectDetailsPage projectDetailsPage;
    BillingPage billingPage;
    WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        newProjectPage = new NewProjectPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        billingPage = new BillingPage(driver);

    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driver.quit();
    }
}
