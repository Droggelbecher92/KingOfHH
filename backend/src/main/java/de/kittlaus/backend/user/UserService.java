package de.kittlaus.backend.user;

import de.kittlaus.backend.models.user.MyUser;
import de.kittlaus.backend.models.user.MyUserDto;
import de.kittlaus.backend.models.user.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public Optional<MyUser> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public ResponseEntity<MyUserDto> createUser(RegisterUser user) {
        if (!user.getPassword().equals(user.getPasswordAgain())){
            return new ResponseEntity<>(MyUserDto.builder().build(),HttpStatus.CONFLICT);
        }
        if (userRepo.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body(MyUserDto.builder().build());
        }
        MyUser myUser = MyUser.builder()
                .password(user.getHashedPassword())
                .username(user.getUsername())
                .role("USER")
                .build();
        userRepo.save(myUser);
        return ResponseEntity.ok(MyUserDto.builder().role("USER").username(myUser.getUsername()).build());
    }
}
