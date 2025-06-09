package aed;

//P = cantidad de usuarios
//n = cantidad de transacciones

public class Berretacoin {
    private heapArray<Usuario> ListaUsuarios;
    private heapArray<Transaccion> ListaTransacciones;
    private Transaccion mayorTransaccion;
    private int montoMedioUltimoBloque;
    private ListaEnlazada<Transaccion[]> cadena;

    public Berretacoin(int n_usuarios) { // O(P)
        this.cadena = new ListaEnlazada<Transaccion[]>();
        this.ListaUsuarios = new heapArray<>(n_usuarios);
        for (int i = 0; i < n_usuarios; i++) {
            ListaUsuarios.insertar(new Usuario(i + 1, 0));
        }
    }

    public void agregarBloque(Transaccion[] transacciones) { // O(n * log(P))
        this.ListaTransacciones = new heapArray<>(transacciones.length);
        this.montoMedioUltimoBloque = 0;
        for (Transaccion tx : transacciones) {
            montoMedioUltimoBloque += tx.monto();
            ListaTransacciones.insertar(tx);
            ListaUsuarios.actualizar(tx.id_comprador(), -(tx.monto()));
            ListaUsuarios.actualizar(tx.id_vendedor(), tx.monto());

        }
        cadena.agregarAtras(transacciones);

    }

    public Transaccion txMayorValorUltimoBloque() { // O(1)
        return ListaTransacciones.obtener(0);
    }

    public Transaccion[] txUltimoBloque() { // O(n)
        Transaccion[] ultima = cadena.ultimo();
        return ultima;
    }

    public int maximoTenedor() { // O(1)
        Usuario maximoTenedor = ListaUsuarios.obtener(0);
        return maximoTenedor.getId();
    }

    public int montoMedioUltimoBloque() {
        return montoMedioUltimoBloque / ListaTransacciones.cantidadElementos();
    }

    public void hackearTx() {
        throw new UnsupportedOperationException("Implementar!");
    }
}
