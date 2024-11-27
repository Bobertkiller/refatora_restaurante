public class RestauranteApplication {
    private ClienteService clienteService;
    private CardapioService cardapioService;
    private PedidoService pedidoService;
    private CozinhaService cozinhaService;
    private PagamentoService pagamentoService;
    private RelatorioService relatorioService;
    
    // Todos os serviços fortemente acoplados
    // Banco de dados único
    // Lógica de negócio misturada
} 