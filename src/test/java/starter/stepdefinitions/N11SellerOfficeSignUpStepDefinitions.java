package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import starter.n11SellerOffice.sign_up.SignUpAsUser;
import starter.n11SellerOffice.navigation.NavigateTo;
import starter.n11SellerOffice.user_interface.SellerOfficeSignUpPageElements;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;


public class N11SellerOfficeSignUpStepDefinitions {

    Actor actor = Actor.named("patikabootcampers onur");

    @Managed
    WebDriver webDriver;

    @Given("user launch browser and open the sign up page")
    public void userLaunchBrowserAndOpenTheSignUpPage() {
        //browseri açıyoruz
        actor.can(BrowseTheWeb.with(webDriver));
        actor.wasAbleTo(NavigateTo.theN11SellerOfficeHomePage());

    }

    @When("user signed in seller office with valid credentials without clicking the contract box")
    public void userSignedInSellerOfficeWithValidCredentialsWithoutClickingTheContractBox() {
        actor.attemptsTo(SignUpAsUser.signUpThePage(
                "onur.25673",
                "123123",
                "123123",
                "onur.25673@gmail.com",
                "onur.25673@gmail.com",
                "pati_store"
                ));
    }

    @Then("user sees error message")
    public void userSeesErrorMessage() {
        actor.attemptsTo(
                WaitUntil.the(SellerOfficeSignUpPageElements.CONTRACT_ERROR_MESSAGE,isVisible()).forNoMoreThan(5).seconds(),
                Ensure.that(SellerOfficeSignUpPageElements.CONTRACT_ERROR_MESSAGE).isDisplayed(),
                Ensure.that(SellerOfficeSignUpPageElements.CONTRACT_ERROR_MESSAGE).hasText("Lütfen sözleşmeyi kabul ettiğinizi onaylayın.")
        );
    }

}
