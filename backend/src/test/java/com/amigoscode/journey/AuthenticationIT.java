package com.amigoscode.journey;

import com.amigoscode.auth.AuthenticationRequest;
import com.amigoscode.auth.AuthenticationResponse;
import com.amigoscode.customer.CustomerDTO;
import com.amigoscode.customer.CustomerRegistrationRequest;
import com.amigoscode.jwt.JWTUtil;
import com.amigoscode.model.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationIT {

    public static final String AUTHENTICATION_URI = "/api/v1/auth/login";
    public static final String CUSTOMER_URI = "/api/v1/customers";
    @Autowired
    private WebTestClient webTestClient;
    private static final Random random = new Random();

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void test() {
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String fullName = fakerName.fullName();
        String email = fakerName.lastName() + UUID.randomUUID() + "@foubar.com";
        int age = random.nextInt(1, 100);
        Gender gender = Gender.FEMALE;

        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                fullName,
                email,
                "password",
                age,
                gender
        );

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                email,
                customerRegistrationRequest.password()
        );

        webTestClient.post()
                .uri(AUTHENTICATION_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isUnauthorized();

        //send post request
        webTestClient.post().uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customerRegistrationRequest), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        EntityExchangeResult<AuthenticationResponse> result = webTestClient.post()
                .uri(AUTHENTICATION_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authenticationRequest), AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<AuthenticationResponse>() {
                })
                .returnResult();
        String token = result.getResponseHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

        CustomerDTO customerDTO = result.getResponseBody().customerDTO();
        String username = customerDTO.username();
        assertThat(jwtUtil.isTokenValid(token, username));
        assertThat(customerDTO.email()).isEqualTo(email);
        assertThat(customerDTO.age()).isEqualTo(age);
        assertThat(customerDTO.name()).isEqualTo(fullName);
        assertThat(customerDTO.gender()).isEqualTo(gender);
        assertThat(customerDTO.username()).isEqualTo(email);
        assertThat(customerDTO.roles()).isEqualTo(List.of("ROLE_USER"));

    }

}
