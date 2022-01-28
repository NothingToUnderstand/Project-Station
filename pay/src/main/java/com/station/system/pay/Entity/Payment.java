package com.station.system.pay.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payment")
public class Payment {
    @Id
    @Column(name = "id_payment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;
    @Column(name = "id_pas_list")
    private Long idPasList;
    @Column(name = "id_ticket")
    private Long idTicket;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
}