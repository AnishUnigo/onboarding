package com.customer.onboarding.constant;

public enum Status {

    SUCCESS("Success"),
    FAILURE("Failure"),
    PENDING("Pending"),
    ERROR("Error"),
    IN_PROGRESS("In Progress");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
