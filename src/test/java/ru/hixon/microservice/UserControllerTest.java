package ru.hixon.microservice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hixon.microservice.dto.UserDataDTO;
import ru.hixon.microservice.entity.Role;
import ru.hixon.microservice.entity.User;
import ru.hixon.microservice.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserControllerTest extends AbstractComponentTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canSignUpTest() {
        // Create first CLIENT user
        String password1 = "f34tQ@rQrqaw32@";
        String username1 = "userName1111";
        Role role1 = Role.ROLE_CLIENT;

        String jwtToken1 = createUser(password1, username1, role1);

        // Create second ADMIN user
        String password2 = "password2";
        String username2 = "userName222";
        Role role2 = Role.ROLE_ADMIN;

        String jwtToken2 = createUser(password2, username2, role2);
    }

    @Test
    public void canLogInTest() {
        // Create user
        String password1 = "f34tQ@rQrqaw32@";
        String username1 = UUID.randomUUID().toString();
        Role role1 = Role.ROLE_CLIENT;

        String jwtToken1 = createUser(password1, username1, role1);

        // try to log-in
        final String baseUrl = "http://localhost:" + port + "/users/signin";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("username", username1)
                .queryParam("password", password1);

        HttpEntity<?> entity = new HttpEntity<>(null);

        ResponseEntity<String> signInResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                String.class);

        Assert.assertEquals(200, signInResponse.getStatusCodeValue());

        String jwtToken = signInResponse.getBody();
        Assert.assertTrue(StringUtils.hasText(jwtToken));
    }

    @Test
    public void canRefreshTokenTest() {
        // Create user
        String password1 = UUID.randomUUID().toString();
        String username1 = UUID.randomUUID().toString();
        Role role1 = Role.ROLE_CLIENT;

        String jwtToken1 = createUser(password1, username1, role1);

        // Try refresh token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken1);

        final String baseUrl = "http://localhost:" + port + "/users/refresh";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> signInResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        Assert.assertEquals(200, signInResponse.getStatusCodeValue());

        String jwtToken = signInResponse.getBody();
        Assert.assertTrue(StringUtils.hasText(jwtToken));
    }

    private String createUser(String password, String username, Role role) {
        final String baseUrl = "http://localhost:" + port + "/users";

        UserDataDTO signUpRequest = new UserDataDTO();
        signUpRequest.setPassword(password);
        signUpRequest.setRoles(List.of(role));
        signUpRequest.setUsername(username);

        ResponseEntity<String> signUpResponse = restTemplate.postForEntity(baseUrl + "/signup", signUpRequest, String.class);
        Assert.assertEquals(200, signUpResponse.getStatusCodeValue());

        String jwtToken = signUpResponse.getBody();
        Assert.assertTrue(StringUtils.hasText(jwtToken));

        return jwtToken;
    }
}
