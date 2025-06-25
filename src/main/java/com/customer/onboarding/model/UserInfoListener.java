package com.customer.onboarding.model;

import com.customer.onboarding.util.EncryptionUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class UserInfoListener {
    @PrePersist
    @PreUpdate
    public void encryptSsn(UserInfo userInfo) {
        if (userInfo.getSsn() != null && !userInfo.getSsn().isEmpty() && !userInfo.getSsn().startsWith("ENC:")) {
            userInfo.setSsn("ENC:" + EncryptionUtil.encrypt(userInfo.getSsn()));
        }
    }
}