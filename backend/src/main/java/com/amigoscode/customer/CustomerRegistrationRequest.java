package com.amigoscode.customer;

import com.amigoscode.model.Gender;

public record CustomerRegistrationRequest(
        String name,
        String email,
        String password,
        Integer age,
        Gender gender
) {
}
