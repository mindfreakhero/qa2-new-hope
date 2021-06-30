package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.util.Map;

public class WeatherStepDefs {
    private long cityId;
    private WeatherResponse response;

    @Given("city ID: {long}")
    public void set_city_id(long cityId) {
        //peremennaja urovja classa = peremennaja metoda;
        this.cityId = cityId;
    }

    @When("we are requesting weather data")
    public void request_weather() throws JsonProcessingException {
        WeatherRequester requester = new WeatherRequester();
        response = requester.getWeatherData(cityId);
    }

    @Then("coordinates are:")
    public void check_coords(Map<String, Double> coords) {
        //(dannije v teste (coord.by key table), sravnitj s responsom)
        Assertions.assertEquals(coords.get("lon"), response.getCoord().getLon(), "Incorrect Coord lon!");
        Assertions.assertEquals(coords.get("lat"), response.getCoord().getLat(), "Incorrect Coord lat!");

    }

}
