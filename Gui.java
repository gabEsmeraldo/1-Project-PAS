import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Gui {

  JFrame frame;
  JTextArea textArea;
  JTextField InputNomeCliente;
  JTextField InputPrincipal;
  JTextField InputAcompanhamento;
  JTextField InputBebida;
  JTextField InputPosicao;
  Controller controller;

  public Gui() {
    controller = Controller.getInstance();
    frame = new JFrame();
    frame.setSize(1000, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setTitle("Kalzone");
    frame.setLayout(new BorderLayout());

    frame.add(new JLabel("Kalzone"), BorderLayout.NORTH);

    textArea = new JTextArea();
    textArea.setEditable(false);
    frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

    JPanel formPanel = new JPanel(new GridLayout(5, 2, 8, 8));
    formPanel.add(new JLabel("Cliente:"));
    InputNomeCliente = new JTextField();
    formPanel.add(InputNomeCliente);

    formPanel.add(new JLabel("Principal:"));
    InputPrincipal = new JTextField();
    formPanel.add(InputPrincipal);

    formPanel.add(new JLabel("Acompanhamento:"));
    InputAcompanhamento = new JTextField();
    formPanel.add(InputAcompanhamento);

    formPanel.add(new JLabel("Bebida:"));
    InputBebida = new JTextField();
    formPanel.add(InputBebida);

    formPanel.add(new JLabel("Posição (0..n):"));
    InputPosicao = new JTextField();
    formPanel.add(InputPosicao);

    frame.add(formPanel, BorderLayout.WEST);

    JPanel buttons = new JPanel(new GridLayout(3, 2, 8, 8));
    JButton btnAdicionar = new JButton("Adicionar");
    JButton btnListar = new JButton("Listar");
    JButton btnGet = new JButton("Buscar por posição");
    JButton btnAlterar = new JButton("Alterar por posição");
    JButton btnRemoverPrimeiro = new JButton("Remover primeiro");
    JButton btnRemoverPos = new JButton("Remover por posição");

    buttons.add(btnAdicionar);
    buttons.add(btnListar);
    buttons.add(btnGet);
    buttons.add(btnAlterar);
    buttons.add(btnRemoverPrimeiro);
    buttons.add(btnRemoverPos);

    frame.add(buttons, BorderLayout.SOUTH);

    btnAdicionar.addActionListener(e -> {
      String cliente = InputNomeCliente.getText();
      String principal = InputPrincipal.getText();
      String acomp = InputAcompanhamento.getText();
      String bebida = InputBebida.getText();
      controller.adicionarPedido(cliente, principal, acomp, bebida);
      JOptionPane.showMessageDialog(frame, "Pedido adicionado");
      limparCampos(false);
      refreshList();
    });

    btnListar.addActionListener(e -> refreshList());

    btnGet.addActionListener(e -> {
      Integer pos = parsePosicao();
      if (pos == null) return;
      Pedido p = controller.getPedido(pos);
      if (p == null) {
        JOptionPane.showMessageDialog(frame, "Pedido não encontrado");
        return;
      }
      InputNomeCliente.setText(p.getCliente());
      InputPrincipal.setText(p.getPrincipal());
      InputAcompanhamento.setText(p.getAcompanhamento());
      InputBebida.setText(p.getBebida());
    });

    btnAlterar.addActionListener(e -> {
      Integer pos = parsePosicao();
      if (pos == null) return;
      controller.alterarPedido(
        pos,
        InputPrincipal.getText(),
        InputAcompanhamento.getText(),
        InputBebida.getText()
      );
      JOptionPane.showMessageDialog(frame, "Pedido alterado");
      refreshList();
    });

    btnRemoverPrimeiro.addActionListener(e -> {
      controller.removerPedido();
      JOptionPane.showMessageDialog(frame, "Primeiro pedido removido");
      refreshList();
    });

    btnRemoverPos.addActionListener(e -> {
      Integer pos = parsePosicao();
      if (pos == null) return;
      controller.removerPedido(pos);
      JOptionPane.showMessageDialog(frame, "Pedido removido na posição");
      refreshList();
    });

    frame.setVisible(true);
    refreshList();
  }

  private void refreshList() {
    String lista = controller.listarPedidos();
    textArea.setText(lista);
  }

  private Integer parsePosicao() {
    try {
      return Integer.parseInt(InputPosicao.getText().trim());
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(frame, "Informe uma posição válida");
      return null;
    }
  }

  private void limparCampos(boolean limparPosicao) {
    InputNomeCliente.setText("");
    InputPrincipal.setText("");
    InputAcompanhamento.setText("");
    InputBebida.setText("");
    if (limparPosicao) InputPosicao.setText("");
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Gui());
  }
}
