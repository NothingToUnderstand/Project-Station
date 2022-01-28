package com.tickets.pay.station.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="info")
public class Info {
    @Id
    @Column(name = "id_info")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInfo;
    @Column(name = "id_ticket")
    private Long idTicket;
    @Column(name = "id_pasList")
    private Long idPasList;
    @Column(name = "status")
    private String status;
}
