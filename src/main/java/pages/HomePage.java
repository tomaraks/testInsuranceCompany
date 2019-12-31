package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;
    private By figureBox = By.className("rvs-star-svg");
    private By reviewTab = By.partialLinkText("Reviews");
    private By ratingBox = By.className("rating-box-wrapper");
    private By ratings = By.className("rvs-svg");
    private By reviewParent = By.cssSelector(".review-action.ng-enter-element");
    private By ratingsB = By.className("review-stat-box");
    private By selectB = By.tagName("path");
    private By writeReview = By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review");
    private By dropdownList = By.cssSelector(".dropdown-item");
    private By dropdownSelect = By.cssSelector(".dropdown-placeholder");
    private By write = By.tagName("textarea");
    private By submitButton = By.cssSelector(".sbn-action.semi-bold-font.btn.fixed-w-c.tall");
    private By confirmationHeader = By.cssSelector(".rvc-header");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void giveRatings(int index) {
        selectReviewTab();
        WebElement figure = driver.findElement(ratingsB).findElement(reviewParent).findElement(ratings).
                findElement(ratingBox).findElements(figureBox).get(index - 1).findElement(selectB);
        Actions actions = new Actions(driver);
        actions.moveToElement(figure).build().perform();
        selectRatings(index);
    }

    private void selectRatings(int index) {
        try {
            WebElement figure2 = driver.findElement(ratingsB).findElement(reviewParent).findElement(ratings).
                    findElement(ratingBox).findElements(figureBox).get(index - 1).findElement(selectB);
            Actions actions = new Actions(driver);
            actions.click(figure2).build().perform();
        } catch (StaleElementReferenceException ex) {
            WebElement figure2 = driver.findElement(ratingsB).findElement(reviewParent).findElement(ratings).
                    findElement(ratingBox).findElements(figureBox).get(index - 1).findElement(selectB);
            Actions actions = new Actions(driver);
            actions.click(figure2).build().perform();
        }
    }

    private void selectReviewTab() {
        driver.findElement(reviewTab).click();
    }

    public void selectFromDropDown(String option) {
        driver.findElement(writeReview).findElement(dropdownSelect).click();
        for (WebElement select : driver.findElement(writeReview).findElements(dropdownList)) {
            if (select.getText().equalsIgnoreCase(option)) {
                select.click();
            }
        }
    }

    public void writeAReview(String review) {
        driver.findElement(writeReview).findElement(write).sendKeys(review);
    }

    public void clickSubmit() {
        driver.findElement(writeReview).findElement(submitButton).click();
    }

    public ProfilePage navigateToProfile() {
        return new ProfilePage(driver);
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationHeader).getText();
    }
}
