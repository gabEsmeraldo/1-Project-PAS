import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenceManager {

  private static PersistenceManager instance;

  public static PersistenceManager getInstance() {
    if (instance == null) {
      instance = new PersistenceManager();
    }
    return instance;
  }

  public void exportarParaCSV(Pedido[] pedidos) throws IOException {
    if (pedidos == null || pedidos.length == 0) {
      throw new IOException("Não há pedidos para exportar");
    }

    String filename = "PedidosSalvos.csv";

    try (FileWriter writer = new FileWriter(filename)) {
      for (int i = 0; i < pedidos.length; i++) {
        Pedido pedido = pedidos[i];
        StringBuilder line = new StringBuilder();
        line
          .append((i + 1))
          .append("-")
          .append(pedido.getCliente())
          .append(",")
          .append(pedido.getPrincipal())
          .append(",")
          .append(pedido.getAcompanhamento())
          .append(",")
          .append(pedido.getBebida());

        if (i < pedidos.length - 1) {
          line.append(";");
        }

        writer.write(line.toString());

        if (i < pedidos.length - 1) {
          writer.write("\n");
        }
      }
    }
  }

  public Pedido[] importarDeCSV() throws IOException {
    String filename = "PedidosSalvos.csv";
    java.io.File file = new java.io.File(filename);

    if (!file.exists()) {
      return new Pedido[0];
    }

    java.util.List<Pedido> pedidos = new java.util.ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }

        String[] pedidosLine = line.split(";");

        for (String pedidoStr : pedidosLine) {
          if (pedidoStr.trim().isEmpty()) {
            continue;
          }

          int dashIndex = pedidoStr.indexOf('-');
          if (dashIndex == -1) {
            continue;
          }

          String pedidoData = pedidoStr.substring(dashIndex + 1);
          String[] campos = pedidoData.split(",");

          if (campos.length >= 4) {
            String cliente = campos[0].trim();
            String principal = campos[1].trim();
            String acompanhamento = campos[2].trim();
            String bebida = campos[3].trim();

            pedidos.add(new Pedido(cliente, principal, acompanhamento, bebida));
          }
        }
      }
    }

    return pedidos.toArray(new Pedido[0]);
  }
}
