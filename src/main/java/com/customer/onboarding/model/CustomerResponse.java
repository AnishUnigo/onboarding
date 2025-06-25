package com.customer.onboarding.model;

import com.customer.onboarding.constant.Message;
import com.customer.onboarding.constant.Status;
import lombok.Builder;

@Builder
public record CustomerResponse(Status status,
                               Message message,
                               UserInfo userInfo) {

}
