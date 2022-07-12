package de.kittlaus.backend.user;


import de.kittlaus.backend.models.user.MyUser;
import de.kittlaus.backend.models.user.MyUserDto;
import de.kittlaus.backend.models.user.RegisterUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MyUserDto> createUser(@RequestBody RegisterUser user) {
        user.setHashedPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @GetMapping("/me")
    public ResponseEntity<MyUserDto> me(Principal principal) {
        MyUser byUsername = userService.findByUsername(principal.getName()).orElseThrow();
        Optional<MyUserDto> dto = Optional.of(MyUserDto.builder().username(byUsername.getUsername()).role(byUsername.getRole()).build());
        return ResponseEntity.of(dto);
    }



}
