package de.kittlaus.backend;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.kittlaus.backend.models.security.Credentials;
import de.kittlaus.backend.models.security.Token;
import de.kittlaus.backend.models.user.MyUserDto;
import de.kittlaus.backend.models.user.RegisterUser;
import de.kittlaus.backend.user.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KingOfHHIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepo userRepo;

    @AfterEach
    void cleanupDb() {
        userRepo.deleteAll();
    }

    //Login and Register
    @Test
    void shouldRegisterNewUserWithValidInputs(){
        //GIVEN
        RegisterUser testuser = new RegisterUser("Franz", "abcd", "abcd");
        //WHEN
        ResponseEntity<MyUserDto> myUserDtoResponseEntity = restTemplate.postForEntity("/api/user", testuser, MyUserDto.class);
        //THEN
        assertThat(myUserDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        MyUserDto actual = myUserDtoResponseEntity.getBody();
        assert actual != null;
        assertThat(actual.getRole()).isEqualTo("USER");
        assertThat(actual.getUsername()).isEqualTo("Franz");
    }
    @Test
    void shouldFailCreateNewUserDifferentPasswords(){
        //GIVEN
        RegisterUser testuser = new RegisterUser("Franz", "abcd1", "abcd");
        //WHEN
        ResponseEntity<MyUserDto> myUserDtoResponseEntity = restTemplate.postForEntity("/api/user", testuser, MyUserDto.class);
        //THEN
        assertThat(myUserDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }
    @Test
    void shouldFailCreateNewUserWithExistingUsername(){
        //GIVEN
        RegisterUser testuser = new RegisterUser("Franz", "abcd", "abcd");
        //WHEN
        restTemplate.postForEntity("/api/user", testuser, MyUserDto.class);
        ResponseEntity<MyUserDto> myUserDtoResponseEntity = restTemplate.postForEntity("/api/user", testuser, MyUserDto.class);
        //THEN
        assertThat(myUserDtoResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void shouldGetTokenWithValidCredentials(){
        //GIVEN
        RegisterUser testuser = new RegisterUser("Franz", "abcd", "abcd");
        Credentials testCredentials = new Credentials("Franz", "abcd");
        //WHEN
        restTemplate.postForEntity("/api/user", testuser, MyUserDto.class);
        ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity("/auth", testCredentials, Token.class);
        //THEN
        assertThat(tokenResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(tokenResponseEntity.getBody()).isNotNull();
    }
    @Test
    void shouldNotGetTokenWithInvalidCredentials(){
        //GIVEN
        RegisterUser testuser = new RegisterUser("Franz", "abcd", "abcd");
        Credentials testCredentials = new Credentials("Franz1", "abcd");
        Credentials testCredentials2 = new Credentials("Franz1", "abcd");
        //WHEN
        restTemplate.postForEntity("/api/user", testuser, MyUserDto.class);
        ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity("/auth", testCredentials, Token.class);
        ResponseEntity<Token> tokenResponseEntity2 = restTemplate.postForEntity("/auth", testCredentials2, Token.class);
        //THEN
        assertThat(tokenResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(tokenResponseEntity2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
