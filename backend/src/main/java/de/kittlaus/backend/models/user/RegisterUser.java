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

}
