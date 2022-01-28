package com.tickets.pay.station.Service;

import java.util.List;

import com.tickets.pay.station.Entity.PassageList;

import org.springframework.stereotype.Service;

@Service
public interface PassageListService {
    List<PassageList> findAll();
    PassageList findById(Long id);
    void savePas(PassageList passageList);
    void deleteById(Long id);
    
}
