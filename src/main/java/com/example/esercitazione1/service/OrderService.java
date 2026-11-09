package com.example.esercitazione1.service;

import com.example.esercitazione1.repository.model.StatoEnum;
import com.example.esercitazione1.service.dto.OrderDTO;
import com.example.esercitazione1.service.dto.StaticsDTO;

import java.util.List;


public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders(StatoEnum statoOrdine);
    OrderDTO updateStatus(Long id, StatoEnum statoOrdine);
    List<StaticsDTO> getOrderStatistics();


}
