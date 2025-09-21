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
  }

  public void removerPedido() {
    filaDeEspera.removerPedido();
  }

  public void removerPedido(int posicao) {
    filaDeEspera.removerPedido(posicao);
  }
}
