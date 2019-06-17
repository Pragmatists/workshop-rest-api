package design.builder.rest;

public class UserJson {
    public String firstName;
    public String lastName;
    public String email;

    public UserJson(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
