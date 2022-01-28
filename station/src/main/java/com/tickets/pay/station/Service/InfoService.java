package com.tickets.pay.station.Service;

import java.util.List;

import com.tickets.pay.station.Entity.Info;

import org.springframework.stereotype.Service;
@Service
public interface InfoService {
    Info findById(Long id);
    void save(Info info);
    Info findByIdTicket(Long idTicket);
    List<Info> findAll();
}
