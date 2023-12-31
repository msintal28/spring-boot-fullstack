package com.amigoscode.customer;

import com.amigoscode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerDao dao;
    @Mock
    PasswordEncoder passwordEncoder;
    private CustomerService underTest;
    private final CustomerDTOMapper customerDTOMapper = new CustomerDTOMapper();

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(dao, passwordEncoder, customerDTOMapper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCustomers() {
        //When
        underTest.getAllCustomers();

        //Then
        verify(dao).selectAllCustomers();
    }

    @Test
    void getCustomer() {
        //Given
        int customerId = 1;
        Customer customer = new Customer();
        when(dao.selectCustomerById(customerId)).thenReturn(Optional.of(customer));

        CustomerDTO expected = customerDTOMapper.apply(customer);

        //When
        CustomerDTO actual = underTest.getCustomer(customerId);


        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCustomerFailed() {
        //Given
        int customerId = 1;
        when(dao.selectCustomerById(customerId)).thenReturn(Optional.empty());

        //When


        //Then
        assertThatThrownBy(() -> {
            underTest.getCustomer(customerId);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void addCustomer() {
        //Given

        //When

        //Then
    }

    @Test
    void deleteCustomerById() {
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