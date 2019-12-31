package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfilePage;

public class RatingsTest extends BaseTest {
    private final String WALLET_HUB_PROFILE_URL = "http://wallethub.com/profile/";
    private final String USERNAME = "xxx";
    private final String EMAIL = "xxx@yahoo.in";
    private final String PASSWORD = "xxx";
    private final String COMPANY = "test_insurance_company";
    private final int RATINGS = 4;
    private final String PRODUCT_TO_SELECT = "Health Insurance";
    private final String REVIEW_TO_WRITE = "Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing Testing ";
    private final String EXPECTED_PROFILE_NAME = "xxx xxx | WalletHub";
    private final String EXPECTED_SUCCESSFUL_MESSAGE = "Awesome!";

    private HomePage homePage;
    private ProfilePage profilePage;

    @Test
    public void login() {
        loginPage.enterEmailPassword(EMAIL, PASSWORD);
        Assert.assertEquals(loginPage.getProfileName(), EXPECTED_PROFILE_NAME, "Wrong Profile Name!!!");

        getWindowManager().goTo(WALLET_HUB_PROFILE_URL + COMPANY);
        homePage = loginPage.navigateToInsuranceCompany();
        homePage.giveRatings(RATINGS);
        homePage.selectFromDropDown(PRODUCT_TO_SELECT);
        homePage.writeAReview(REVIEW_TO_WRITE);
        homePage.clickSubmit();
        Assert.assertTrue(homePage.getConfirmationMessage().contains(EXPECTED_SUCCESSFUL_MESSAGE), "Wrong Confirmation Message!!!");

        getWindowManager().goTo(WALLET_HUB_PROFILE_URL + USERNAME);
        profilePage = homePage.navigateToProfile();
        Assert.assertTrue(profilePage.reviewIsPresent(), "Review Feed Is Not Present In Profile!!!");
    }

}
