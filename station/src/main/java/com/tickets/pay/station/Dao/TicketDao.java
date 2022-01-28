package com.tickets.pay.station.Dao;

import com.tickets.pay.station.Entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TicketDao extends JpaRepository<Ticket,Long> {
    
}
