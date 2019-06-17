package design.builder;

import com.fasterxml.jackson.databind.JsonNode;
import design.builder.service.User;
import design.builder.service.UserService;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void testApp() throws IOException {
        userService.add(new User("John", "Snow", "john.snow@starks.com"));

        Request request = new Request.Builder()
                .url(apiUrl("/api"))
                .build();
        Response response = client.newCall(request).execute();

        assertThat(response.code()).isEqualTo(HttpStatus.OK.value());
        JsonNode json = bodyFrom(response);
        assertThat(json.get(0).get("firstName").textValue()).isEqualTo("John");
        assertThat(json.get(0).get("lastName").textValue()).isEqualTo("Snow");
        assertThat(json.get(0).get("email").textValue()).isEqualTo("john.snow@starks.com");
    }
}
