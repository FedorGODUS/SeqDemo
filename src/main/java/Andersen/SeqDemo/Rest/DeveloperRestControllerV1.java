package Andersen.SeqDemo.Rest;

import Andersen.SeqDemo.Model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {
    private List<User> DEVELOPERS = getUsers();

    public DeveloperRestControllerV1() throws SQLException, ClassNotFoundException {
    }


    @GetMapping
    public List<User> getAll() {
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('read')")
    public User getById(@PathVariable Long id) {
        return DEVELOPERS.stream().filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public User create(@RequestBody User developer) {
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public void deleteById(@PathVariable Long id) {
        this.DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
    }


    private List<User> getUsers() throws ClassNotFoundException, SQLException {
        List<User> USERS = new LinkedList<>();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AndersenT1","postgres","1234");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT * FROM users ");
        while (result.next()){
            USERS.add(new User(result.getString("email"),result.getString("first_name"),result.getString("second_name"),result.getInt("role_id"),result.getString("status")));
        }
        return USERS;
    }

}
