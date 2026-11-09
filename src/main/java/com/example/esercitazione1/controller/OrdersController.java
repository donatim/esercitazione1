package com.example.esercitazione1.controller;

import com.example.esercitazione1.controller.model.request.OrderRequest;
import com.example.esercitazione1.controller.model.response.OrderResponse;
import com.example.esercitazione1.repository.model.StatoEnum;
import com.example.esercitazione1.service.dto.OrderDTO;
import com.example.esercitazione1.service.dto.StaticsDTO;
import com.example.esercitazione1.service.impl.OrderServiceImpl;
import com.example.esercitazione1.service.mapper.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/ordini")
@Slf4j
public class OrdersController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    @Operation(summary = "Crea un nuovo ordine", description = "Crea un nuovo ordine con i dettagli forniti nel corpo della richiesta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine creato con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class), examples = @ExampleObject(value = "{\"id\": 1, \"nomeCliente\": \"Mario\", \"cognomeCliente\": \"Rossi\", \"emailCliente\": \"mario.rossi@example.com\", \"telefonoCliente\": \"1234567890\", \"indirizzoSpedizione\": \"Via Roma 1\", \"cittaSpedizione\": \"Roma\", \"capSpedizione\": \"00100\", \"prodotto\": \"Prodotto A\", \"quantita\": 2, \"prezzoUnitario\": 10.0}"))),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Errore interno del server", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        log.atInfo().log("Creating order for customer: {} {}", orderRequest.getNomeCliente(), orderRequest.getCognomeCliente());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setNomeCliente(orderRequest.getNomeCliente());
        orderDTO.setCognomeCliente(orderRequest.getCognomeCliente());
        orderDTO.setEmailCliente(orderRequest.getEmailCliente());
        orderDTO.setTelefonoCliente(orderRequest.getTelefonoCliente());
        orderDTO.setIndirizzoSpedizione(orderRequest.getIndirizzoSpedizione());
        orderDTO.setCittaSpedizione(orderRequest.getCittaSpedizione());
        orderDTO.setCapSpedizione(orderRequest.getCapSpedizione());
        orderDTO.setProdotto(orderRequest.getProdotto());
        orderDTO.setQuantita(orderRequest.getQuantita());
        orderDTO.setPrezzoUnitario(orderRequest.getPrezzoUnitario());
        OrderResponse orderResponse = orderMapper.mapToResponse(orderService.createOrder(orderDTO));
        log.atInfo().log("Order created with ID: {}", orderResponse.getId());
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Recupera un ordine per ID", description = "Recupera i dettagli di un ordine specifico utilizzando l'ID dell'ordine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine recuperato con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class), examples = @ExampleObject(value = "{\"id\": 1, \"nomeCliente\": \"Mario\", \"cognomeCliente\": \"Rossi\", \"emailCliente\": \"mario.rossi@example.com\", \"telefonoCliente\": \"1234567890\", \"indirizzoSpedizione\": \"Via Roma 1\", \"cittaSpedizione\": \"Roma\", \"capSpedizione\": \"00100\", \"prodotto\": \"Prodotto A\", \"quantita\": 2, \"prezzoUnitario\": 10.0}"))),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {}", id);
        OrderDTO orderDTO = orderService.getOrderById(id);
        OrderResponse orderResponse = orderMapper.mapToResponse(orderDTO);
        log.atInfo().log("Order fetched: {}", orderResponse);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    @Operation(summary = "Recupera tutti gli ordini", description = "Recupera i dettagli di tutti gli ordini, opzionalmente filtrati per stato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordini recuperati con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class)))
    })
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam(required = false) StatoEnum statoOrdine) {
        log.info("Fetching all orders with status: {}", statoOrdine);
        List<OrderDTO> orderDTOs = orderService.getAllOrders(statoOrdine);
        List<OrderResponse> orderResponses = orderDTOs.stream()
                .map(orderMapper::mapToResponse)
                .toList();
        return ResponseEntity.ok(orderResponses);
    }

    @PutMapping("/{id}/stato")
    @Operation(summary = "Aggiorna lo stato di un ordine", description = "Aggiorna lo stato di un ordine specifico utilizzando l'ID dell'ordine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stato dell'ordine aggiornato con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id, @RequestParam String statoOrdine) {
        log.info("Updating status for order with ID: {}", id);
        OrderDTO orderDTO = orderService.updateStatus(id, StatoEnum.valueOf(statoOrdine));
        OrderResponse orderResponse = orderMapper.mapToResponse(orderDTO);
        log.atInfo().log("Order status updated: {}", orderResponse);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/statistiche/stato")
    @Operation(summary = "Recupera statistiche sugli ordini per stato", description = "Recupera le statistiche sugli ordini filtrate per stato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistiche recuperate con successo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StaticsDTO.class)))
    })
    public ResponseEntity<List<StaticsDTO>> getOrderStatistics() {
        log.info("Fetching order statistics.");
        List<StaticsDTO> statistics = orderService.getOrderStatistics();
        log.atInfo().log("Order statistics fetched: {}", statistics);
        return ResponseEntity.ok(statistics);
    }
}
