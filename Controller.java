public class Controller {

  private static Controller instance;

  private ListaDuplamenteEncadeada filaDeEspera;

  private Controller() {
    filaDeEspera = new ListaDuplamenteEncadeada();
  }

  public static synchronized Controller getInstance() {
    if (instance == null) {
      instance = new Controller();
    }
    return instance;
  }

  public void adicionarPedido(
    String cliente,
    String principal,
    String acompanhamento,
    String bebida
  ) {
    filaDeEspera.adicionarPedido(
      new Pedido(cliente, principal, acompanhamento, bebida)
    );
    salvarAutomaticamente();
  }

  public String listarPedidos() {
    return filaDeEspera.listarPedidos();
  }

  public Pedido getPedido(int posicao) {
    return filaDeEspera.getPedido(posicao);
  }

  public void alterarPedido(
    int posicao,
    String principal,
    String acompanhamento,
    String bebida
  ) {
    Pedido pedido = filaDeEspera.getPedido(posicao);
    Pedido.alterarPedido(pedido, principal, acompanhamento, bebida);
    salvarAutomaticamente();
  }

  public void removerPedido() {
    filaDeEspera.removerPedido();
    salvarAutomaticamente();
  }

  public void removerPedido(int posicao) {
    filaDeEspera.removerPedido(posicao);
    salvarAutomaticamente();
  }

  public ListaDuplamenteEncadeada getFilaDeEspera() {
    return filaDeEspera;
  }

  public void carregarPedidosSalvos() {
    try {
      Pedido[] pedidos = PersistenceManager.getInstance().importarDeCSV();
      for (Pedido pedido : pedidos) {
        filaDeEspera.adicionarPedido(pedido);
      }
    } catch (Exception e) {
      System.out.println(
        "Não foi possível carregar pedidos salvos: " + e.getMessage()
      );
    }
  }

  private void salvarAutomaticamente() {
    try {
      Pedido[] pedidos = filaDeEspera.getAllPedidos();
      PersistenceManager.getInstance().exportarParaCSV(pedidos);
    } catch (Exception e) {
      System.out.println("Erro ao salvar automaticamente: " + e.getMessage());
    }
  }
}
