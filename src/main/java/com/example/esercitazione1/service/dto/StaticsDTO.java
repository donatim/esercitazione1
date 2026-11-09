package com.example.esercitazione1.service.dto;

import com.example.esercitazione1.repository.model.StatoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaticsDTO {
    private StatoEnum statoOrdine;
    private Long numeroOrdini;
}
