package aed;

//P = cantidad de usuarios
//n = cantidad de transacciones

public class Berretacoin {
    private Usuario[] listaUsuarios;
    private heapArray<Usuario> heapUsuarios;
    private heapArray<Transaccion> ListaTransacciones;
    private int montoMedioUltimoBloque;
    private ListaEnlazada<Transaccion[]> cadena;

    public Berretacoin(int n_usuarios) { // O(P)
        this.cadena = new ListaEnlazada<Transaccion[]>();
        this.heapUsuarios = new heapArray<>(n_usuarios);
        this.listaUsuarios = new Usuario[n_usuarios];
        for (int i = 0; i < n_usuarios; i++) {
            Usuario usuario = new Usuario(i + 1, 0);
            heapUsuarios.insertarHandle(i, usuario);
            listaUsuarios[i] = usuario;
        }
    }

    public void agregarBloque(Transaccion[] transacciones) { // O(n * log(P))
        this.ListaTransacciones = new heapArray<>(transacciones.length);
        this.montoMedioUltimoBloque = 0;
        for (Transaccion tx : transacciones) {
            ListaTransacciones.insertar(tx);
            int comprador = tx.id_comprador();
            int vendedor = tx.id_vendedor();

            if (comprador != 0) {
                montoMedioUltimoBloque += tx.monto();
                int saldoAnterior = listaUsuarios[comprador - 1].getSaldo();
                int nuevoSaldo = saldoAnterior - tx.monto();
                Usuario compradorActualizado = new Usuario(comprador, nuevoSaldo);
                heapUsuarios.actualizar(comprador - 1, compradorActualizado);
                listaUsuarios[comprador - 1] = compradorActualizado;
            }

            // vendedor
            int saldoAnterior = listaUsuarios[vendedor - 1].getSaldo();
            int nuevoSaldo = saldoAnterior + tx.monto();
            Usuario vendedorActualizado = new Usuario(vendedor, nuevoSaldo);
            heapUsuarios.actualizar(vendedor - 1, vendedorActualizado);
            listaUsuarios[vendedor - 1] = vendedorActualizado;

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
        Usuario maximoTenedor = heapUsuarios.obtener(0);
        return maximoTenedor.getId();
    }

    public double montoMedioUltimoBloque() {
        int transaccionesSinCreacion = ListaTransacciones.cantidadElementos() - 1;

        return transaccionesSinCreacion == 0 ? 0
                : montoMedioUltimoBloque / transaccionesSinCreacion;
    }

    public void hackearTx() {
        throw new UnsupportedOperationException("Implementar!");
    }
}
