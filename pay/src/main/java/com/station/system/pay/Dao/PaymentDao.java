package com.station.system.pay.Dao;

import com.station.system.pay.Entity.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaymentDao extends JpaRepository<Payment,Long> {

    Payment getById(Long id);
    
}
