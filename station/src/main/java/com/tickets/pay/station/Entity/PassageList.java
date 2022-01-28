package com.tickets.pay.station.Entity;

import java.sql.Time;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="passage_list")
public class PassageList {
    @Id
    @Column(name = "id_pas_list")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasList;
    @Column(name = "departure")
    private String departure;
    @Column(name = "arrival")
    private String arrival;
    @Column(name = "time")
    private Time time ;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "tickets")
    private Integer tickets;

}
