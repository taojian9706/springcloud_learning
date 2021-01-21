package com.donglan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donglan.dao.PaymentDao;
import com.donglan.pojo.Payment;
import com.donglan.service.PaymentService;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 15:04:32
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int add(Payment payment) {
        return paymentDao.add(payment);
    }

    @Override
    public List<Payment> getList() {
        return paymentDao.getList();
    }
}
