package com.tickets.pay.station.Controller;

import java.io.IOException;
import java.net.UnknownHostException;

import com.tickets.pay.station.Entity.PassageList;
import com.tickets.pay.station.Entity.Ticket;
import com.tickets.pay.station.Service.InfoService;
import com.tickets.pay.station.Service.PassageListService;
import com.tickets.pay.station.Service.TicketService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RequestMapping("/ticket")
@RestController
@NoArgsConstructor
@AllArgsConstructor
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;
    @Autowired
    PassageListService passageListService;
    @Autowired
    InfoService infoService;

    // method to make post request to pay service
    public void makeApiCall(Ticket pay) {
        final String uri = "http://localhost:8080/payment";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(uri, pay, String.class, headers);
    }

    // Save tickets
    @PostMapping(value = "/save")
    public ResponseEntity<?> createTicket(@RequestParam(name = "idPas", required = true) Long idPas,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "surname", required = true) String surname) throws UnknownHostException, IOException {
        log.info("Params into ticket/save:{}", idPas, name, surname);

        // Get PasList ID
        PassageList passage = this.passageListService.findById(idPas);
        log.info("Passage:{}", passage);

        // Checking if such PasList exists and its cost is equals
        if (idPas <= 3) {
            log.info("I'am here to save ticket");
            Ticket ticket = new Ticket();
            ticket.setIdPasList(idPas);
            ticket.setName(name);
            ticket.setSurname(surname);
            // making ticket cost with negative value
            ticket.setCost(-1 * passage.getCost());
            log.info("Cost:{}", -1 * passage.getCost());
            this.ticketService.saveTicket(ticket);

            // Post for pay Service on localhost:8080
            makeApiCall(ticket);

            // Substraction from number of tickets
            log.info("Passage Number of tickets:{}", passage.getTickets());
            passage.setTickets(passage.getTickets() - 1);
            log.info("Rest number of tickets:{}", passage.getTickets());
            passageListService.savePas(passage);

            // returning Ticket id
            Long idTicket = ticket.getIdTicket();
            return ResponseEntity.ok("Your ticket id: " + idTicket);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Passage with " + idPas + " do not exist or cost is wrong");
        }
    }

    // Delets ticket from DB if status is Fail
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteTicket(@RequestParam(name = "id") Long id) {
        this.ticketService.deleteTicket(id);
        PassageList passage = this.passageListService.findById(id);
        log.info("Passage:{}", passage);

        // Returns the number of tickets
        log.info("Passage Number of tickets:{}", passage.getTickets());
        passage.setTickets(passage.getTickets() + 1);
        log.info("Rest number of tickets:{}", passage.getTickets());
        passageListService.savePas(passage);

        return ResponseEntity.ok("Ticket with id: " + id + "is deleted");
    }

    @GetMapping("/get/passage")
    public ResponseEntity<?> getPassage(@RequestBody Long id) {
        PassageList passage = passageListService.findById(id);
        return ResponseEntity.ok("Your Passage is" + passage);
    }
}
