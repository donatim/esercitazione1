package com.example.esercitazione1.service.impl;

import com.example.esercitazione1.exception.InvalidOrderStatusTransitionException;
import com.example.esercitazione1.exception.OrderNotFoundException;
import com.example.esercitazione1.repository.OrderRepository;
import com.example.esercitazione1.repository.model.OrderEntity;
import com.example.esercitazione1.repository.model.StatoEnum;
import com.example.esercitazione1.service.OrderService;
import com.example.esercitazione1.service.dto.OrderDTO;
import com.example.esercitazione1.service.dto.StaticsDTO;
import com.example.esercitazione1.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCodiceOrdine(UUID.randomUUID().toString().substring(0, 30));
        orderEntity.setNomeCliente(orderDTO.getNomeCliente());
        orderEntity.setCognomeCliente(orderDTO.getCognomeCliente());
        orderEntity.setEmailCliente(orderDTO.getEmailCliente());
        orderEntity.setTelefonoCliente(orderDTO.getTelefonoCliente());
        orderEntity.setIndirizzoSpedizione(orderDTO.getIndirizzoSpedizione());
        orderEntity.setCittaSpedizione(orderDTO.getCittaSpedizione());
        orderEntity.setCapSpedizione(orderDTO.getCapSpedizione());
        orderEntity.setProdotto(orderDTO.getProdotto());
        orderEntity.setQuantita(orderDTO.getQuantita());
        orderEntity.setPrezzoUnitario(orderDTO.getPrezzoUnitario());
        orderEntity.setTotale(orderDTO.getQuantita() * orderDTO.getPrezzoUnitario());
        orderEntity.setStatoOrdine(StatoEnum.RICEVUTO);
        orderEntity.setDataOrdine(LocalDateTime.now());
        return orderMapper.mapToDTO(orderRepository.save(orderEntity));
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapToDTO)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<OrderDTO> getAllOrders(StatoEnum statoOrdine) {
        if (statoOrdine != null) {
            return orderRepository.findAll()
                    .stream()
                    .filter(order -> order.getStatoOrdine() == statoOrdine)
                    .map(orderMapper::mapToDTO)
                    .toList();
        } else {
            return orderRepository.findAll()
                    .stream()
                    .map(orderMapper::mapToDTO)
                    .toList();
        }
    }

    @Override
    public OrderDTO updateStatus(Long id, StatoEnum statoOrdine) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);
        if (!isValidTransition(orderEntity.getStatoOrdine(), statoOrdine)) {
            throw new InvalidOrderStatusTransitionException();
        }
        orderEntity.setStatoOrdine(statoOrdine);
        return orderMapper.mapToDTO(orderRepository.save(orderEntity));
    }

    private boolean isValidTransition(StatoEnum currentStatus, StatoEnum newStatus) {
        if (newStatus == null) {
            return false;
        }
        return switch (currentStatus) {
            case RICEVUTO -> newStatus == StatoEnum.IN_LAVORAZIONE || newStatus == StatoEnum.ANNULLATO;
            case IN_LAVORAZIONE -> newStatus == StatoEnum.SPEDITO || newStatus == StatoEnum.ANNULLATO;
            case SPEDITO, ANNULLATO -> false;
        };
    }

    @Override
    public List<StaticsDTO> getOrderStatistics() {
        return orderRepository.countOrdersByStatoOrdine();
    }
}
