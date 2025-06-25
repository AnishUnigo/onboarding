package com.customer.onboarding.service;

import com.customer.onboarding.constant.Message;
import com.customer.onboarding.constant.Status;
import com.customer.onboarding.model.Address;
import com.customer.onboarding.model.CustomerResponse;
import com.customer.onboarding.model.UserInfo;
import com.customer.onboarding.repository.AddressRepository;
import com.customer.onboarding.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * This method is responsible for onboarding a new customer.
     *
     * @param userInfo The information of the user to be onboarded.
     * @return A CustomerResponse containing the status and message of the operation.
     */
    @Transactional
    public CustomerResponse onboardCustomer(UserInfo userInfo) {
        // Logic to onboard a customer
        logger.info("Onboarding customer: {}", userInfo.toString());
        Address savedAddress = addressRepository.save(userInfo.getAddress());
        UserInfo savedUserInfo = userInfoRepository.save(userInfo);
        // Set the saved address to the user info
        if(savedAddress != null) {
            logger.info("Address saved successfully: {}", savedAddress);
            savedUserInfo.setAddress(savedAddress);
        } else {
            logger.error("Failed to save address for user: {}", userInfo);
        }

        if (savedUserInfo != null) {
            // Customer is successfully saved in db
            logger.info("Customer onboarded successfully: {}", savedUserInfo);
            return createCustomerResponse(Status.SUCCESS, Message.USER_CREATED, savedUserInfo);
        } else {
            // Handle the case where user saving failed
            logger.error("Failed to onboard customer: {}", userInfo);
            return createCustomerResponse(Status.FAILURE, Message.USER_CREATION_FAILED, savedUserInfo);
        }
    }

    /**
     * This method is responsible for updating an existing customer's information.
     *
     * @param userInfo The updated information of the user.
     * @return A CustomerResponse containing the status and message of the operation.
     */
    public CustomerResponse updateCustomer(UserInfo userInfo) {
        Optional<UserInfo> existingUser = userInfoRepository.findById(userInfo.getId());
        if (existingUser.isEmpty()) {
            return  createCustomerResponse(Status.FAILURE, Message.USER_NOT_FOUND, null);
        }
        UserInfo updatedUser = existingUser.get();
        updatedUser.setFirstName(userInfo.getFirstName());
        updatedUser.setLastName(userInfo.getLastName());
        updatedUser.setEmail(userInfo.getEmail());
        updatedUser.setPhoneNumber(userInfo.getPhoneNumber());
        updatedUser.setSsn(userInfo.getSsn());
        updatedUser.setAddress(userInfo.getAddress());
        try {
            UserInfo savedUser = userInfoRepository.save(updatedUser);
            return createCustomerResponse(Status.SUCCESS, Message.USER_UPDATED, savedUser);
        } catch (Exception e) {
            return createCustomerResponse(Status.FAILURE, Message.FAILED_TO_UPDATE, null);
        }
    }

    /**
     * This method is responsible for deleting a customer.
     *
     * @param userId The ID of the user to be deleted.
     * @return A CustomerResponse containing the status and message of the operation.
     */
    public CustomerResponse deleteCustomer(String userId) {
        // Logic to delete a customer
        // Assuming user deletion is successful
        return CustomerResponse.builder()
                .message(Message.USER_DELETED)
                .status(Status.SUCCESS)
                .userInfo(null) // No user info returned after deletion
                .build();
    }

    /**
     * This method is responsible for retrieving a customer's information.
     *
     * @param userId The ID of the user whose information is to be retrieved.
     * @return A CustomerResponse containing the status, message, and user information.
     */
    public CustomerResponse getCustomer(String userId) {
        // Logic to retrieve a customer
        // Assuming we found the user
        return CustomerResponse.builder()
                .message(Message.USER_RETRIEVED)
                .status(Status.SUCCESS)
                .build();
    }

    /**
     * This method creates a CustomerResponse object with the given status, message, and user information.
     *
     * @param status The status of the operation.
     * @param message The message associated with the operation.
     * @param userInfo The user information to be included in the response.
     * @return A CustomerResponse object containing the provided information.
     */
    private CustomerResponse createCustomerResponse(Status status, Message message, UserInfo userInfo) {
        return CustomerResponse.builder()
                .message(message)
                .status(status)
                .userInfo(userInfo)
                .build();
    }
}
