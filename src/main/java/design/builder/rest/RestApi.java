package design.builder.rest;

import design.builder.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestApi {
    private UserService userService;

    public RestApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserJson> getAll() {
        return userService.all().stream()
                .map(u -> new UserJson(u.getFirstName(), u.getLastName(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
