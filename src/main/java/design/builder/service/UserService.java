package design.builder.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserService {
    private List<User> users = new ArrayList<>();

    public Collection<User> all() {
        return users;
    }

    public void clear() {
        users.clear();
    }

    public void add(User user) {
        users.add(user);
    }
}
