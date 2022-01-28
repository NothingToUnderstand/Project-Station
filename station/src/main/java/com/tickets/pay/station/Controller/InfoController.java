package com.tickets.pay.station.Controller;

import java.util.ArrayList;

import com.tickets.pay.station.Entity.Info;
import com.tickets.pay.station.Service.InfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;



    private static final Logger log = LoggerFactory.getLogger(InfoController.class);

    @GetMapping("/get")
    public ResponseEntity<?> getInfoTicket(@RequestParam(value = "idTicket", required = true) Long idTicket) {
        log.info("idTicket:{}",idTicket);
        // Searching for info with ticket id
        Info info = new Info();
        ArrayList<Info> allInfo = (ArrayList<Info>) this.infoService.findAll();
        log.info("Size:{}", allInfo.size());
        boolean found = false;
        for (Info inf : allInfo) {
            if (idTicket == inf.getIdTicket()) {
                info = infoService.findById(inf.getIdInfo());
                found = true;
                break;
            }
        }
        if (found = false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no such ticket: " + idTicket);
        }
        log.info("Info to find:{}", info);

        return ResponseEntity.ok("Information about your ticket: \n" + "Ticket ID: " + info.getIdTicket()
                + "\n" + "Passage ID: " + info.getIdPasList()
                + "\n" + "Status: " + info.getStatus());
    }

    // Saving info about ticket
    @PostMapping("/save")
    public ResponseEntity<?> saveInfo(@RequestBody Info info) {
        log.info("Info to save:{}", info);
        this.infoService.save(info);
        Long idInfo = info.getIdInfo();

        return ResponseEntity.ok("Info saved with id: " + idInfo);
    }



}
