package com.example.esercitazione1.controller.model.response;

import com.example.esercitazione1.repository.model.StatoEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
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
