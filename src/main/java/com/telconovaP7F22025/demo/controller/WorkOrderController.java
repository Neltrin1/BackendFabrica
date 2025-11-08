package com.telconovaP7F22025.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telconovaP7F22025.demo.dto.order.CreateOrderRequest;
import com.telconovaP7F22025.demo.model.WorkOrder;
import com.telconovaP7F22025.demo.service.WorkOrderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "WorkOrder Controller", description = "Maneja la creación y gestión de Órdenes de Trabajo")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    // Este es tu nuevo endpoint: POST /api/orders
    @PostMapping
    public ResponseEntity<WorkOrder> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        WorkOrder newOrder = workOrderService.createWorkOrder(request);
        
        // Devolvemos un 201 Created y el objeto de la orden recién creada
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }
}