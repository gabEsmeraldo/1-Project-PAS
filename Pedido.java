public class Pedido {
    protected String cliente;
    protected String principal;
    protected String acompanhamento;
    protected String bebida;

    public Pedido(String cliente, String principal, String acompanhamento, String bebida) {
        this.cliente = cliente;
        this.principal = principal;
        this.acompanhamento = acompanhamento;
        this.bebida = bebida;
    }

    public String getPrincipal() {
        return principal;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public String getAcompanhamento() {
        return acompanhamento;
    }
    public void setAcompanhamento(String acompanhamento) {
        this.acompanhamento = acompanhamento;
    }
    public String getBebida() {
        return bebida;
    }
    public void setBebida(String bebida) {
        this.bebida = bebida;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public static void alterarPedido(Pedido pedido, String principal, String acompanhamento, String bebida){
        pedido.setPrincipal(principal);
        pedido.setAcompanhamento(acompanhamento);
        pedido.setBebida(bebida);
    }

    public void listarPedido(){
        System.out.println("Pedido de: " + cliente + ", Um " + principal + ", acompanhado por: " + acompanhamento + " e uma bebida: " + bebida);
    }
}
