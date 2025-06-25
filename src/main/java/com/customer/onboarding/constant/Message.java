package com.customer.onboarding.constant;

public enum Message {
    USER_CREATED("User created successfully"),
    USER_UPDATED("User updated successfully"),
    USER_DELETED("User deleted successfully"),
    USER_RETRIEVED("User information retrieved successfully"),
    USER_NOT_FOUND("User not found"),
    FAILED_TO_UPDATE("Failed to update user information"),
    INVALID_INPUT("Invalid input provided"),
    SERVER_ERROR("Internal server error occurred"),
    UNAUTHORIZED_ACCESS("Unauthorized access"),
    FORBIDDEN("Access forbidden"),
    USER_CREATION_FAILED("User creation failed");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
