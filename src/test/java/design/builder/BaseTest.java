package design.builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import design.builder.rest.RestApi;
import design.builder.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static java.lang.String.format;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = RANDOM_PORT)
public abstract class BaseTest {

    protected final OkHttpClient client = new OkHttpClient();
    @LocalServerPort
    private Integer port;
    @Autowired
    RestApi restApi;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        userService.clear();
    }

    protected String apiUrl(final String path) {
        return format("http://localhost:%d" + path, port);
    }

    protected JsonNode bodyFrom(Response response) {
        try {
            return new ObjectMapper().readTree(response.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
