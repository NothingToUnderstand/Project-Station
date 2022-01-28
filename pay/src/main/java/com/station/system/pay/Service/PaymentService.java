package com.station.system.pay.Service;

import java.util.List;

import com.station.system.pay.Entity.Payment;

import org.springframework.stereotype.Service;


@Service
public interface PaymentService {
    Payment findPayById(Long id);
    void savePay(Payment pay);
    List<Payment> findAll();

}
