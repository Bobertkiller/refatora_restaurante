package com.restaurante.pedido.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long clienteId;
    private String status;
    private BigDecimal valorTotal;
    private LocalDateTime dataPedido;
    
    @PrePersist
    public void prePersist() {
        dataPedido = LocalDateTime.now();
        status = "NOVO";
    }
} 