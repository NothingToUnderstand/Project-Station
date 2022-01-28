package com.tickets.pay.station.ServiceImplemetation;

import java.util.List;

import com.tickets.pay.station.Dao.InfoDao;
import com.tickets.pay.station.Entity.Info;
import com.tickets.pay.station.Service.InfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {
@Autowired
InfoDao infoDao;


    @Override
    public Info findById(Long id) {
        return this.infoDao.getById(id);
    }

    @Override
    public void save(Info info) {
        this.infoDao.save(info);
        
    }

    @Override
    public List<Info> findAll() {
        return this.infoDao.findAll(); 
    }

    @Override
    public Info findByIdTicket(Long idTicket) {
        
        return this.findByIdTicket(idTicket);
    }

  
    
}
