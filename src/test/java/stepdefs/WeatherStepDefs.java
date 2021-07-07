package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Weather;
import model.WeatherResponse;
import org.junit.jupiter.api.Assertions;
import requesters.WeatherRequester;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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

    @Then("weather contains:")
    public void check_weather(DataTable weather) {
        // predstavljam tabli4ku kak list mappov
        List<Map<String, String>> weathers = weather.asMaps();

        //check spisok pogod po koli4estvu objektov;
        Assertions.assertEquals(weathers.size(), response.getWeather().size());

        //iteriruem skvoz list objektov;
        for (int i = 0; i < weathers.size(); i++) {
            Map<String, String> expectedWeather = weathers.get(i);
            Weather actualWeather = response.getWeather().get(i);

            Assertions.assertEquals(Integer.parseInt(expectedWeather.get("id")), actualWeather.getId(), "Wrong");
            Assertions.assertEquals(expectedWeather.get("main"), actualWeather.getMain(), "Wrong");
            Assertions.assertEquals(expectedWeather.get("description"), actualWeather.getDescription(), "Wrong");
            Assertions.assertEquals(expectedWeather.get("icon"), actualWeather.getIcon(), "Wrong");
        }
    }

    @Then("base is {string}")
    public void check_base(String base) {
        Assertions.assertEquals(base, response.getBase(), "Incorrect base!");
    }

    @Then("main is:")
    public void check_main(Map<String, Double> main) {
        Assertions.assertEquals(main.get("temp"), response.getMain().getTemp(), "Incorrect main temp!");
        Assertions.assertEquals(main.get("pressure"), response.getMain().getPressure(), "Incorrect main pressure!");
        Assertions.assertEquals(main.get("humidity"), response.getMain().getHumidity(), "Incorrect main humidity!");
        Assertions.assertEquals(main.get("temp_min"), response.getMain().getTemp_min(), "Incorrect main temp_min!");
        Assertions.assertEquals(main.get("temp_max"), response.getMain().getTemp_max(), "Incorrect main temp_max!");
    }

    @Then("visibility is {int}")
    public void check_visibility(int visibility) {
        Assertions.assertEquals(visibility, response.getVisibility(), "Incorrect visibility");
    }

    @Then("wind is:")
    public void check_wind(Map<String, Double> wind) {
        Assertions.assertEquals(wind.get("deg"), response.getWind().getDeg(), "Incorrect wind deg!");
        Assertions.assertEquals(wind.get("speed"), response.getWind().getSpeed(), "Incorrect wind speed!");
    }

    @Then("clouds are:")
    public void check_clouds(Map<String, Integer> clouds) {
        Assertions.assertEquals(clouds.get("all"), response.getClouds().getAll(), "Incorrect clouds all!");
    }

    @Then("dt is {int}")
    public void check_dt(int dt) {
        Assertions.assertEquals(dt, response.getDt(), "Incorrect dt");
    }

    @Then("sys is:")
    public void check_sys(Map<String, String> sys) {
        //peredelivaem 4tobi tipi dannih sovpadali
        Assertions.assertEquals(Integer.parseInt(sys.get("type")), response.getSys().getType(), "Incorrect sys type!");
        Assertions.assertEquals(Long.parseLong(sys.get("id")), response.getSys().getId(), "Incorrect sys id!");
        Assertions.assertEquals(Double.parseDouble(sys.get("message")), response.getSys().getMessage(), "Incorrect sys message!");
        Assertions.assertEquals(sys.get("country"), response.getSys().getCountry(), "Incorrect sys country!");
        Assertions.assertEquals(Long.parseLong(sys.get("sunrise")), response.getSys().getSunrise(), "Incorrect sys sunrise!");
        Assertions.assertEquals(Long.parseLong(sys.get("sunset")), response.getSys().getSunset(), "Incorrect sys sunset!");

        LocalDate date = Instant.ofEpochSecond(response.getSys().getSunrise()).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(response.getSys().getSunrise()), ZoneId.systemDefault());
        System.out.println(dateTime);


    }


    @Then("id is {int}")
    public void check_id(int id) {
        Assertions.assertEquals(id, response.getId(), "Incorrect id!");
    }


    @Then("name is {string}")
    public void check_name(String name) {
        Assertions.assertEquals(name, response.getName(), "Incorrect name!");
    }

    @Then("cod is {int}")
    public void check_cod(int cod) {
        Assertions.assertEquals(cod, response.getCod(), "Incorrect cod!");
    }
}
