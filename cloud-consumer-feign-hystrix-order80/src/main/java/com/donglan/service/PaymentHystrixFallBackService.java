package com.donglan.service;

import org.springframework.stereotype.Component;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-20 17:28:00
 */
@Component
public class PaymentHystrixFallBackService implements PaymentHystrixService {
    @Override
    public String isOk(Integer id) {
        return "PaymentHystrixFallBackService isOk";
    }

    @Override
    public String timeout(Integer id) {
        return "PaymentHystrixFallBackService timeout";
    }
}
