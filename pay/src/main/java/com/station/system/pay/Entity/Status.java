package com.station.system.pay.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="status")
public class Status {
    @Id
    @Column(name = "id_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;
    @Column(name = "id_payment")
    private Long idPayment;
    @Column(name = "status")
    private String status;
}
