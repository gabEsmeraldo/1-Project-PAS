public class No {
    private Pedido elemento;
    private No proximo;
    private No anterior;

    public No(Pedido elemento) {
        this.elemento = elemento;
        this.proximo = null;
        this.anterior = null;
    }

    public Pedido getElemento() {
        return elemento;
    }
    public void setElemento(Pedido elemento) {
        this.elemento = elemento;
    }
    public No getProximo() {
        return proximo;
    }
    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
    public No getAnterior() {
        return anterior;
    }
    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
}
