package de.kittlaus.backend.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {

    private String username;
    private String password;
    private String passwordAgain;
    private String hashedPassword;

    public RegisterUser(String username, String password, String passwordAgain) {
        this.username = username;
        this.password = password;
        this.passwordAgain = passwordAgain;
    }
}
