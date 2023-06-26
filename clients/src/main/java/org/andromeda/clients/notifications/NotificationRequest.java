package org.andromeda.clients.notifications;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
