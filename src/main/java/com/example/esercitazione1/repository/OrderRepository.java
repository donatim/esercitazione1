package com.example.esercitazione1.repository;

import com.example.esercitazione1.repository.model.OrderEntity;
import com.example.esercitazione1.service.dto.StaticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("SELECT new com.example.esercitazione1.service.dto.StaticsDTO(o.statoOrdine, COUNT(o)) " +
            "FROM OrderEntity o GROUP BY o.statoOrdine")
    List<StaticsDTO> countOrdersByStatoOrdine();
}
