package com.telconovaP7F22025.demo.service.impl;

import org.springframework.stereotype.Service;

import com.telconovaP7F22025.demo.dto.order.CreateOrderRequest;
import com.telconovaP7F22025.demo.model.WorkOrder;
import com.telconovaP7F22025.demo.repository.WorkOrderRepository;
import com.telconovaP7F22025.demo.service.WorkOrderService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Inyecta el repositorio automáticamente
public class WorkOrderServiceImpl implements WorkOrderService {

    private final WorkOrderRepository workOrderRepository;

    @Override
    @Transactional
    public WorkOrder createWorkOrder(CreateOrderRequest request) {
        // Mapeamos del DTO (lo que nos llega) a la Entidad (lo que guardamos)
        WorkOrder order = new WorkOrder();
        order.setNameCliente(request.nameCliente());
        order.setPhoneCliente(request.phoneCliente());
        order.setTipoServicio(request.tipoServicio());
        order.setPrioridad(request.prioridad());
        order.setDescripcion(request.descripcion());
        
        // Asignamos el estado inicial por defecto
        order.setEstado("PENDIENTE");

        // Guardamos en la base de datos y devolvemos la orden creada
        return workOrderRepository.save(order);
    }
}