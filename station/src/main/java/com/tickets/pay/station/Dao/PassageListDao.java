package com.tickets.pay.station.Dao;

import com.tickets.pay.station.Entity.PassageList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageListDao extends JpaRepository<PassageList, Long> {
    
}
