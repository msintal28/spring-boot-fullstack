package com.amigoscode.customer;

import com.amigoscode.AbstractTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest extends AbstractTestContainer {

    public static final String MAIL = "testmail@gmail.com";
    private CustomerJDBCDataAccessService underTest;
    private CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                getJdbcTemplate(),
                customerRowMapper
        );
    }

    @Test
    void selectAllCustomers() {
        //Given
        Customer customer = new Customer(
                "TestName",
                UUID.randomUUID() + MAIL,
                15
        );
        underTest.insertCustomer(customer);

        //When
        List<Customer> actual = underTest.selectAllCustomers();

        //Then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void selectCustomerById() {
        //Given
        String email = UUID.randomUUID() + MAIL;
        Customer customer = new Customer(
                "TestName",
                email,
                15
        );
        underTest.insertCustomer(customer);
        Long id = underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();

        //When
        Optional<Customer> actual = underTest.selectCustomerById(id.intValue());

        //Then
        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id.intValue());
            assertThat(c.getName()).isEqualTo(customer.getName());
            assertThat(c.getEmail()).isEqualTo(customer.getEmail());
            assertThat(c.getAge()).isEqualTo(customer.getAge());
        });
    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {
        //Given
        int id = -1;

        //When
        Optional<Customer> actual = underTest.selectCustomerById(id);

        //Then
        assertThat(actual).isEmpty();
    }

    @Test
    void insertCustomer() {
        //Given

        //When

        //Then
    }

    @Test
    void existsPersonWithEmail() {
        //Given
        String email = UUID.randomUUID() + MAIL;
        Customer customer = new Customer(
                "TestName",
                email,
                15
        );
        underTest.insertCustomer(customer);

        //When
        boolean expected = underTest.existsPersonWithEmail(email);

        //Then
        assertThat(expected).isTrue();
    }

    @Test
    void existsPersonWithId() {
        //Given

        //When

        //Then
    }

    @Test
    void deleteCustomer() {
        //Given

        //When

        //Then
    }

    @Test
    void updateCustomer() {
        //Given

        //When

        //Then
    }
}