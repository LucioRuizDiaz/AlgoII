package aed;

public class Berretacoin {
    private heapArray<Integer> ListaUsuarios;
    private heapArray<Transaccion> ListaTransacciones;

    public Berretacoin(int n_usuarios) {
        this.ListaUsuarios = new heapArray<>(n_usuarios);
    }

    public void agregarBloque(Transaccion[] transacciones) {
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion txMayorValorUltimoBloque() {
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque() {
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor() {
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque() {
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx() {
        throw new UnsupportedOperationException("Implementar!");
    }
}
