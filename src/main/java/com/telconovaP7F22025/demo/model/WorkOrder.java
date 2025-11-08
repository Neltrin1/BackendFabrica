package com.telconovaP7F22025.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "work_orders")
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    // Simplificamos los datos del cliente por ahora
    @Column(nullable = false)
    private String nameCliente;

    @Column(nullable = false)
    private String phoneCliente;

    @Column(nullable = false)
    private String tipoServicio;

    @Column(nullable = false)
    private String prioridad;

    @Column(nullable = false)
    private String estado; // Ej. "PENDIENTE", "ASIGNADA", "CERRADA"

    @Column(length = 1000)
    private String descripcion;
    
    // Spring se encargará de estas fechas automáticamente
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
}