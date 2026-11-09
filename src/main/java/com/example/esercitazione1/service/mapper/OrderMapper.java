package com.example.esercitazione1.service.mapper;

import com.example.esercitazione1.controller.model.response.OrderResponse;
import com.example.esercitazione1.repository.model.OrderEntity;
import com.example.esercitazione1.service.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

   public abstract OrderDTO mapToDTO(OrderEntity entity);
   public abstract OrderEntity mapToEntity(OrderDTO dto);
   public abstract OrderResponse mapToResponse(OrderDTO dto);
}
