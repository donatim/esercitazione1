package com.example.esercitazione1.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 20, max = 30)
    private String codiceOrdine;

    @NotNull
    @Length(min = 2, max = 50)
    private String nomeCliente;

    @NotNull
    @Length(min = 2, max = 50)
    private String cognomeCliente;

    @NotNull
    @Length(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "email not valid")
    private String emailCliente;

    @Length(max = 20)
    private String telefonoCliente;

    @NotNull
    @Length(min = 5, max = 120)
    private String indirizzoSpedizione;

    @NotNull
    @Length(min = 2, max = 50)
    private String cittaSpedizione;

    @NotNull
    @Pattern(regexp = "^[0-9]{5}$", message = "CAP must be 5 digits")
    private String capSpedizione;

    @NotNull
    @Length(min = 2, max = 80)
    private String prodotto;

    @NotNull
    @Min(value = 1, message = "quantita must be greater than 0")
    @Max(value = 100, message = "quantita must be less than or equal to 100")
    private Integer quantita;

    @NotNull
    @Min(value = 1, message = "prezzoUnitario must be greater than 0")
    private Double prezzoUnitario;

    @NotNull
    private Double totale;

    @NotNull
    private StatoEnum statoOrdine;

    @NotNull
    private LocalDateTime dataOrdine;
}
