package com.tickets.pay.station.Service;

import com.tickets.pay.station.Entity.Ticket;

import org.springframework.stereotype.Service;
@Service
public interface TicketService {
    void saveTicket(Ticket ticket);
    Ticket findById(Long id);
    void deleteTicket(Long id);

}
