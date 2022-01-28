package com.tickets.pay.station.ServiceImplemetation;

import java.util.List;

import com.tickets.pay.station.Dao.PassageListDao;
import com.tickets.pay.station.Entity.PassageList;
import com.tickets.pay.station.Service.PassageListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PassageListServiceImpl implements PassageListService {
    @Autowired
    PassageListDao passageListDao;

    @Override
    public List<PassageList> findAll() {
        return this.passageListDao.findAll();
    }

    @Override
    public PassageList findById(Long id) {   
        return this.passageListDao.getById(id);
    }

    @Override
    public void savePas(PassageList passageList) {
       this.passageListDao.save(passageList);
        
    }

    @Override
    public void deleteById(Long id) {
        this.passageListDao.deleteById(id);
        
    }
    
}
