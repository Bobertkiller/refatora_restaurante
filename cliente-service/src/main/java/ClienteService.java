@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final KafkaTemplate<String, ClienteEvent> kafkaTemplate;

    @PostMapping("/clientes")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = repository.save(cliente);
        kafkaTemplate.send("cliente-eventos", new ClienteEvent(novoCliente));
        return novoCliente;
    }
} 