package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private WebDriver driver;
    private By reviewHeader = By.linkText("Test Insurance Company");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean reviewIsPresent() {
        return driver.findElement(reviewHeader).isDisplayed();
    }

}
