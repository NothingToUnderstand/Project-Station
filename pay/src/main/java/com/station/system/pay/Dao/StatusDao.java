package com.station.system.pay.Dao;

import com.station.system.pay.Entity.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDao extends JpaRepository<Status,Long> {

    Status getById(Long id);
    
}
