package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("user navigates to application")
    public void given_step() {
      System.out.println("Given > " + Thread.currentThread().getId());
    }

    @When("^user login into application$")
    public void when_step() {
      System.out.println("When > " + Thread.currentThread().getId());
    }

    @Then("user must be on Homepage")
        public void then_step() {
      System.out.println("Then > " + Thread.currentThread().getId());
    }
}
