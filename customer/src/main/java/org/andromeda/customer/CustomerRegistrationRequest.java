package org.andromeda.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
