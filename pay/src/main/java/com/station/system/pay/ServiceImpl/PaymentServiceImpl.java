package com.station.system.pay.ServiceImpl;

import java.util.List;

import com.station.system.pay.Dao.PaymentDao;
import com.station.system.pay.Entity.Payment;
import com.station.system.pay.Service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    PaymentDao paymentDao;

    @Override
    public Payment findPayById(Long id) {
        return this.paymentDao.getById(id);
    }

    @Override
    public void savePay(Payment pay) {
        this.paymentDao.save(pay);
        
    }

    @Override
    public List<Payment> findAll() {
        return this.paymentDao.findAll();
    }
    
}
