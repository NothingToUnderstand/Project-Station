package com.tickets.pay.station.Dao;

import com.tickets.pay.station.Entity.Info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface InfoDao extends JpaRepository<Info,Long> {
    Info findByIdTicket(Long idTicket);
}
