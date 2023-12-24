package com.amigoscode.journey;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerDTO;
import com.amigoscode.customer.CustomerRegistrationRequest;
import com.amigoscode.model.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerIntegrationTest {

    public static final String CUSTOMER_URI = "/api/v1/customers";
    @Autowired
    private WebTestClient webTestClient;
    private static final Random random = new Random();

    @Test
    void canRegisterACustomer() {
        //create Registration Request
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String fullName = fakerName.fullName();
        String email = fakerName.lastName() + UUID.randomUUID() + "@foubar.com";
        int age = random.nextInt(1, 100);
        Gender gender = Gender.FEMALE;

        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                fullName,
                email,
                "password", age,
                gender
        );
        //send post request
        String jwtToken = webTestClient.post().uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customerRegistrationRequest), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(HttpHeaders.AUTHORIZATION)
                .get(0);


        //get all customers
        List<CustomerDTO> allCustomers = webTestClient.get()
                .uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format("bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<CustomerDTO>() {
                })
                .returnResult()
                .getResponseBody();



        var id = allCustomers.stream()
                .filter(c -> c.email().equals(email))
                .map(CustomerDTO::id)
                .findFirst()
                .orElseThrow();

        //make sure that customer is present
        CustomerDTO expectedCustomer = new CustomerDTO(
                id,
                fullName,
                email,
                gender,
                age,
                List.of("ROLE_USER"),
                email
        );

        assertThat(allCustomers)
                //.usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(expectedCustomer);

        //get customer by id
        webTestClient.get()
                .uri(CUSTOMER_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, String.format("bearer %s"), jwtToken)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<CustomerDTO>() {
                })
                .isEqualTo(expectedCustomer);
    }

    @Test
    void canDeleteCustomer() {
        //create Registration Request
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String fullName = fakerName.fullName();
        String email = fakerName.lastName() + UUID.randomUUID() + "@foubar.com";
        Gender gender = Gender.FEMALE;
        int age = random.nextInt(1, 100);

        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                fullName,
                email,
                "password", age,
                gender
        );
        //send post request
        webTestClient.post().uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customerRegistrationRequest), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        //get all customers
        List<Customer> allCustomers = webTestClient.get()
                .uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                })
                .returnResult()
                .getResponseBody();


        var id = allCustomers.stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        webTestClient.delete()
                .uri(CUSTOMER_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        //get customer by id
        webTestClient.get()
                .uri(CUSTOMER_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void canUpdateCustomer() {
//create Registration Request
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String fullName = fakerName.fullName();
        String email = fakerName.lastName() + UUID.randomUUID() + "@foubar.com";
        Gender gender = Gender.FEMALE;
        int age = random.nextInt(1, 100);

        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest(
                fullName,
                email,
                "password", age,
                gender
        );
        //send post request
        webTestClient.post().uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(customerRegistrationRequest), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        //get all customers
        List<Customer> allCustomers = webTestClient.get()
                .uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Customer>() {
                })
                .returnResult()
                .getResponseBody();


        var id = allCustomers.stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        String updatedName = "updatedName";
        String updatedEmail = "updatedEmail" + UUID.randomUUID();
        int updatedAge = 15;
        Gender updatedGender = Gender.MALE;

        CustomerRegistrationRequest toBeUpdatedCustomer = new CustomerRegistrationRequest(
                updatedName,
                updatedEmail,
                "password", updatedAge,
                updatedGender
        );

        //update customer
        webTestClient.put()
                .uri(CUSTOMER_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(toBeUpdatedCustomer), CustomerRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();


        Customer expectedCustomer = new Customer(
                id,
                updatedName,
                updatedEmail,
                "password", updatedAge,
                updatedGender
        );
        //get customer by id
        webTestClient.get()
                .uri(CUSTOMER_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<Customer>() {})
                .isEqualTo(expectedCustomer);

    }
}
