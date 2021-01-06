package Andersen.SeqDemo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.sql.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "second_name")
    private String second_name;
    @Column(name = "password")
    private String password;
    @Column(name = "role_id")
    private int role_id;
    @Column(name = "status")
    private String status;






    public User(){}

    public User(String email, String name , String second_name, int role , String status) {
        this.email=email;
        this.name=name;
        this.second_name = second_name;
        this.role_id = role;
        this.status=status;
    }



    public Role getRole(int role_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AndersenT1","postgres","1234");
        PreparedStatement preparedStatement;
        preparedStatement= connection.prepareStatement("select * from roles where id = ?");
        preparedStatement.setInt(1,role_id);
        ResultSet result = preparedStatement.executeQuery();
        return new Role(result.getString("role"));


    }


}
