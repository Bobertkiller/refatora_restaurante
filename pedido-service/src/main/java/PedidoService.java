package com.example.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
public class PedidoService {
    private final PedidoRepository repository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, PedidoEvent> kafkaTemplate;
    
    @Autowired
    public PedidoService(PedidoRepository repository, RestTemplate restTemplate, KafkaTemplate<String, PedidoEvent> kafkaTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @PostMapping("/pedidos")
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        // Validação com serviço de clientes
        ClienteDTO cliente = restTemplate.getForObject(
            "http://cliente-service/clientes/" + pedido.getClienteId(),
            ClienteDTO.class
        );
        
        // Processamento do pedido
        Pedido novoPedido = repository.save(pedido);
        
        // Notifica serviço de cozinha
        kafkaTemplate.send("novos-pedidos", new PedidoEvent(novoPedido));
        
        return novoPedido;
    }
} 