package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private By email_id = By.name("em");
    private By passWord = By.name("pw");
    private By submitButton = By.cssSelector(".btn.blue.center.reg-tabs-bt.touch-element-cl");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmailPassword(String email, String password) {
        driver.findElement(email_id).sendKeys(email);
        driver.findElement(passWord).sendKeys(password);
        driver.findElement(submitButton).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            System.err.println("Exception occurred with message " + ex.getMessage());
        }
    }

    public HomePage navigateToInsuranceCompany() {
        return new HomePage(driver);
    }

    public String getProfileName() {
        return driver.getTitle();
    }

}
