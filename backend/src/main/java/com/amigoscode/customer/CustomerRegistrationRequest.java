package com.amigoscode.customer;

import com.amigoscode.model.Gender;

public record CustomerRegistrationRequest(
        String name,
        String email,
        Integer age,
        Gender gender
) {
}
