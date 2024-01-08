package lk.ijse.ProjectSihina.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String Email;
    private String NIC;
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String NIC) {
        this.userName = userName;
        this.password = password;
        this.NIC = NIC;
    }

    public User(String userId, String firstName, String lastName, String email, String NIC) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
        this.NIC = NIC;
    }
}
