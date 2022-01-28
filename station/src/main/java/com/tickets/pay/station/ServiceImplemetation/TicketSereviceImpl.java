package com.tickets.pay.station.ServiceImplemetation;

import com.tickets.pay.station.Dao.TicketDao;
import com.tickets.pay.station.Entity.Ticket;
import com.tickets.pay.station.Service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TicketSereviceImpl implements TicketService {
    @Autowired
    TicketDao ticketDao;
  
    @Override
    public void saveTicket(Ticket ticket) {
       this.ticketDao.save(ticket);
        
    }

    @Override
    public Ticket findById(Long id) {
        return this.ticketDao.getById(id) ;
    }

    @Override
    public void deleteTicket(Long id) {
        this.ticketDao.deleteById(id);
        
    }
    
}
