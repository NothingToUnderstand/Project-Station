package com.station.system.pay.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataToSend {
   private Long idPasList;
   private Long idTicket;
   private String status;
}
