package com.tickets.pay.station.SchedulerService;

import java.util.List;

import javax.persistence.EntityManager;

import com.tickets.pay.station.Entity.Info;
import com.tickets.pay.station.Entity.PassageList;
import com.tickets.pay.station.Entity.Ticket;
import com.tickets.pay.station.Service.InfoService;
import com.tickets.pay.station.Service.PassageListService;
import com.tickets.pay.station.Service.TicketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ShedulerService {
    private static final Logger log = LoggerFactory.getLogger(ShedulerService.class);

    @Autowired
    InfoService infoService;
    @Autowired
    TicketService ticketService;
    @Autowired
    PassageListService passageListService;
    @Autowired
    EntityManager entityManager;
    // Handler for status
    @Scheduled(initialDelayString = "3000", fixedDelayString = "3000")
    public void chekStatus() throws InterruptedException {
        log.info("Start process");
        
        List<Info> allInfo = this.infoService.findAll();
        for (Info status : allInfo) {
            switch (status.getStatus()) {
                case "New":
                    log.info("Info status:New");
                    log.info("Ticket with id:{} ,has status New", status.getIdTicket());
                    Info info = this.infoService.findById(status.getIdInfo());
                    log.info("Info: {}", info);
                    break;
                case "Done":
                    log.info("Info status:Done");
                    break;

                case "Fail":
                    Ticket ticket = new Ticket();
                    ticket=  entityManager.find(Ticket.class, status.getIdTicket());
                    log.info("Delete:{}", ticket);
                    if (ticket != null) {
                        this.ticketService.deleteTicket(status.getIdTicket());

                        log.info("Info status:Fail");

                        log.info("Ticket with id:{} ,is deleted", status.getIdTicket());

                        // Returns the number of tickets
                        PassageList passage = this.passageListService.findById(status.getIdPasList());
                        log.info("Passage Number of tickets:{}", passage.getTickets());
                        passage.setTickets(passage.getTickets() + 1);
                        log.info("Rest number of tickets:{}", passage.getTickets());
                        passageListService.savePas(passage);

                        break;
                    }

                    log.info("This ticket has been deleted:{}", status.getIdTicket());
                    break;

            }
        }
        log.info("End process");
    }
}
