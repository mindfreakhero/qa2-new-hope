package requesters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.WeatherResponse;
import org.springframework.web.client.RestTemplate;

public class WeatherRequester {
    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?id=";
    private final String POSTFIX = "&appid=b1b15e88fa797225412429c1c50c122a1";

    public WeatherResponse getWeatherData(long id) throws JsonProcessingException {
        String url = PREFIX + id + POSTFIX;
        // sozdali kopiju objekta; using spring-web to make queries and get response;
        RestTemplate restTemplate = new RestTemplate();
        // .zapros na server(url, vozvrat dannih v vide Stringa).tolko telo otveta;
        String json = restTemplate.getForEntity(url, String.class).getBody();

        //using jackson to get objects with data from JSON;
        ObjectMapper mapper = new ObjectMapper();
        // pro4itatj.iz jsona v weatherresponse);
        return mapper.readValue(json, WeatherResponse.class);

    }
}
