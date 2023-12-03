package com.amigoscode.customer;

import com.amigoscode.AbstractTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestContainer {

    @Autowired
    private CustomerRepository underTest;
    public static final String MAIL = "testmail@gmail.com";

    @BeforeEach
    void setup(){

    }

    @Test
    void existsCustomerByEmail() {

    }

    @Test
    void existsCustomerById() {
        //Given
        String email = UUID.randomUUID() + MAIL;
        Customer customer = new Customer(
                "TestName",
                email,
                15
        );
        underTest.save(customer);
        Long id = underTest.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        //When
        boolean actual = underTest.existsCustomerById(id.intValue());

        //Then
        assertThat(actual).isTrue();
    }
}