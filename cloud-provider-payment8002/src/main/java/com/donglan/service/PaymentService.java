package com.donglan.service;

import java.util.List;

import com.donglan.pojo.Payment;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 15:03:51
 */
public interface PaymentService {

    int add(Payment payment);

    List<Payment> getList();
}
