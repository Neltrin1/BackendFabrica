package com.telconovaP7F22025.demo.service;

import com.telconovaP7F22025.demo.dto.order.CreateOrderRequest;
import com.telconovaP7F22025.demo.model.WorkOrder;

public interface WorkOrderService {
    
    /**
     * Crea una nueva orden de trabajo.
     * @param request El DTO con la información de la orden.
     * @return La entidad WorkOrder guardada.
     */
    WorkOrder createWorkOrder(CreateOrderRequest request);
}