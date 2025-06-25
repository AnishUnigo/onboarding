package com.customer.onboarding.controller;

import com.customer.onboarding.constant.Status;
import com.customer.onboarding.model.CustomerResponse;
import com.customer.onboarding.model.UserInfo;
import com.customer.onboarding.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    /**
     * @param userInfo
     * @return
     */
    @PostMapping("/customerservice/onboard")
    public ResponseEntity<CustomerResponse> onboardCustomer(@RequestBody @Valid UserInfo userInfo) {
        // Logic to onboard a customer
        logger.info("In CustomerController : {}", userInfo.getFirstName());
        CustomerResponse customerResponse = customerService.onboardCustomer(userInfo);
        if (customerResponse!=null && customerResponse.status() != Status.SUCCESS) {
            // Handle error case
            return ResponseEntity.ok(customerResponse);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(customerResponse);
    }
}
