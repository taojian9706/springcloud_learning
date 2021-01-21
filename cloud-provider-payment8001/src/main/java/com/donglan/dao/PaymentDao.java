package com.donglan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.donglan.pojo.Payment;

/**
 * @author TAOJIAN
 * @version 1.0
 * @since 2021-01-15 14:53:34
 */
@Mapper
public interface PaymentDao {

    List<Payment> getList();

    int add(Payment payment);
}
