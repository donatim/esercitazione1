package com.example.esercitazione1.service.dto;

import com.example.esercitazione1.repository.model.StatoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String codiceOrdine;
    private String nomeCliente;
    private String cognomeCliente;
    private String emailCliente;
    private String telefonoCliente;
    private String indirizzoSpedizione;
    private String cittaSpedizione;
    private String capSpedizione;
    private String prodotto;
    private Integer quantita;
    private Double prezzoUnitario;
    private Double totale;
    private StatoEnum statoOrdine;
    private LocalDateTime dataOrdine;
}
