package com.example.esercitazione1.controller.model.request;

import lombok.Data;

@Data
public class OrderRequest {
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
}
