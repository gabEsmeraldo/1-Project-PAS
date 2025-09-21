public class ListaDuplamenteEncadeada {

  protected No cabeca;
  protected No cauda;
  protected int tamanho;

  public ListaDuplamenteEncadeada() {
    cabeca = null;
    cauda = null;
  }

  public void quantidadePedidosRestantes() {
    System.out.println(tamanho);
  }

  public void adicionarPedido(Pedido pedido) {
    No no = new No(pedido);
    if (cabeca == null) {
      cabeca = no;
      cauda = no;
    } else {
      cauda.setProximo(no);
      no.setAnterior(cauda);
      cauda = no;
    }
    tamanho++;
  }

  public void removerPedido() {
    if (cabeca != null) {
      cabeca = cabeca.getProximo();
    } else {
      System.out.println("Não há pedidos a serem removidos");
      return;
    }
    tamanho--;
  }

  public void removerPedido(int pos) {
    if (cabeca != null && pos == 0) {
      cabeca = cabeca.getProximo();
    } else if (pos == tamanho - 1) {
      cauda = cauda.getAnterior();
      cauda.setProximo(null);
    } else if (pos < tamanho) {
      int posAtual = 0;
      No cursor = cabeca;
      while (posAtual < pos) {
        cursor = cursor.getProximo();
        posAtual++;
      }
      cursor.getAnterior().setProximo(cursor.getProximo());
      cursor.getProximo().setAnterior(cursor.getAnterior());
    }
    tamanho--;
  }

  public String listarPedidos() {
    if (cabeca == null) {
      return "Nenhum pedido na fila.";
    }
    StringBuilder sb = new StringBuilder();
    No cursor = cabeca;
    int pos = 0;
    while (cursor != null) {
      Pedido p = cursor.getElemento();
      sb
        .append("[")
        .append(pos)
        .append("] ")
        .append(p.getCliente())
        .append(" - ")
        .append(p.getPrincipal())
        .append(", ")
        .append(p.getAcompanhamento())
        .append(", ")
        .append(p.getBebida())
        .append("\n");
      cursor = cursor.getProximo();
      pos++;
    }
    return sb.toString();
  }

  public Pedido getPedido(int pos) {
    if (pos >= tamanho) {
      System.out.println("Posição inexistente");
      return null;
    }
    No cursor = cabeca;
    int posAtual = 0;
    while (posAtual < pos) {
      cursor = cursor.getProximo();
      posAtual++;
    }
    return cursor.getElemento();
  }
}
