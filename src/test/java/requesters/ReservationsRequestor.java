package requesters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Reservation;
import model.ReservationsResponse;
import model.WeatherResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ReservationsRequestor {
    // private final String LINK = "http://qaguru.lv:8089/tickets/getReservations.php";

    public List<Reservation> getReservationsList() throws IOException {

        // RestTemplate rest = new RestTemplate();
       // String json = rest.getForEntity(LINK, String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        URL link = new URL("http://qaguru.lv:8089/tickets/getReservations.php");
        // return mapper.readValue(link, ???);

        return mapper.reader().forType(new TypeReference<List<Reservation>>(){}).readValue(link);
    }

}
