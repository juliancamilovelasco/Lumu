package steps;

import Home.Home;
import groovy.transform.Final;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HomeStepsDefinition {

    WebDriver driver;
    Home home;

    @Given("I navigate to the WordCounter website")
    public void i_navigate_to_the_word_counter_website() {
        driver = new ChromeDriver();
        home = new Home(driver);
        home.goToUrl();
    }

    @When("I input the text {string}")
    public void i_input_the_text(String text) {
        home.sendKeys(text);
    }

    @Then("I should see the word count as {int}")
    public void i_should_see_the_word_count_as(int expectedWordCount) {
        home.countWord(expectedWordCount);
        driver.close();
    }

    @Then("I should see the character count as {int}")
    public void i_should_see_the_character_count_as(int expectedCharCount) {
        home.countCharacter(expectedCharCount);
        driver.close();
    }

    @Then("I should see the keyword density as {string}")
    public void i_should_see_the_keyword_density_as(String expectedKeywordDensity) {
        home.density();
        driver.close();
    }

}
