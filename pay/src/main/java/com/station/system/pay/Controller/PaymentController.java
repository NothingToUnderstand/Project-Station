package com.station.system.pay.Controller;

import java.util.ArrayList;
import com.station.system.pay.Dto.DataToSend;
import com.station.system.pay.Entity.Payment;
import com.station.system.pay.Entity.Status;
import com.station.system.pay.Service.PaymentService;
import com.station.system.pay.Service.StatusService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private PaymentService paymentService;

    private StatusService statusService;

    @Autowired
    public PaymentController(PaymentService paymentService, StatusService statusService) {
        this.paymentService = paymentService;
        this.statusService = statusService;
    }
    

    // Post to save info in infoController
    DataToSend dataToSend = new DataToSend();
    public void makeApiCall(DataToSend info) {
        final String uri = "http://localhost:8081/info/save";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate.postForObject(uri, info, String.class, headers);
    }

    // Save Payment from ticket controller
    @PostMapping("")
    public ResponseEntity<?> savePayment(@RequestBody Payment pay) {
        log.info("I'am here to save Payment");
        log.info("Pay:{}", pay);
        this.paymentService.savePay(pay);
        Long idPay = pay.getIdPayment();

        if (pay.getIdPasList() <= 3) {

            return ResponseEntity.ok(idPay);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Passage with " + pay.getIdPasList() + " do not exist");
        }
    }

    // Buying ticket
    @PostMapping("/pay")
    public ResponseEntity<?> pay(@RequestParam(name = "idTicket", required = true) Long idTicket,
            @RequestParam(name = "cost", required = true) Integer cost) {
        log.info("I'am here to pay");
        // Searching for payment with ticket id
        Payment payment = new Payment();
        ArrayList<Payment> allTickets = (ArrayList<Payment>) this.paymentService.findAll();
        log.info("Size:{}", allTickets.size());

        boolean found = false;
        for (Payment pay : allTickets) {
            if (idTicket == pay.getIdTicket()) {
                payment = paymentService.findPayById(pay.getIdPayment());
                found = true;
                break;
            }
        }
        if (found = false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no such ticket: " + idTicket);
        }
        log.info("Pay payment:{}", payment);

        // cheking the cost
        if (payment.getCost() + cost >= 0) {
            payment.setCost(cost + payment.getCost());
            log.info("Pay cost:{}", payment.getCost());

            // Saving random status and return it
            String body = saveStatus(payment.getIdPayment()).getBody().toString();
            log.info("body:{}", body);
            // Data to send
            DataToSend dataToSend = new DataToSend();
            dataToSend.setIdPasList(payment.getIdPasList());
            dataToSend.setIdTicket(payment.getIdTicket());
            dataToSend.setStatus(body);
            makeApiCall(dataToSend);

            return ResponseEntity.ok("Your payment id: " + payment.getIdPayment());

        }
        int paymentPlus = -1 * payment.getCost();
        log.info("paymentPlus:{}", paymentPlus);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Not enough money to pay,please pay: " + paymentPlus + " ,or more");
    }

    // Saving status
    public ResponseEntity<?> saveStatus(@RequestParam(name = "id") Long id) {
        log.info("ID:{}", id);
        Status status = new Status();
        status.setIdPayment(id);
        this.statusService.saveStatus(status);
        log.info("Status:{}", status);
        return ResponseEntity.ok(status.getStatus());
    }

}
