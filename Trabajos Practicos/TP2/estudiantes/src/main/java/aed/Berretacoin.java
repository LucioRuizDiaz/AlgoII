package aed;

//P = cantidad de usuarios
//n = cantidad de transacciones

public class Berretacoin {
    private ListaEnlazada<heapArray<Transaccion>> cadena;
    private Usuario[] listaUsuarios;
    private heapArray<Usuario> heapUsuarios;
    private heapArray<Transaccion> heapTransacciones;
    private int montoMedioUltimoBloque;

    public Berretacoin(int n_usuarios) { // O(P)
        this.cadena = new ListaEnlazada<heapArray<Transaccion>>();
        this.heapUsuarios = new heapArray<>(n_usuarios);
        this.listaUsuarios = new Usuario[n_usuarios];
        for (int i = 0; i < n_usuarios; i++) {
            Usuario usuario = new Usuario(i + 1, 0);
            heapUsuarios.insertarHandle(i, usuario);
            listaUsuarios[i] = usuario;
        }
    }

    public void agregarBloque(Transaccion[] transacciones) { // O(n * log(P))
        this.heapTransacciones = new heapArray<>(transacciones.length);
        this.montoMedioUltimoBloque = 0;
        for (Transaccion tx : transacciones) {

            heapTransacciones.insertarHandle(tx.ID(), tx);
            int comprador = tx.id_comprador();
            int vendedor = tx.id_vendedor();
            int monto = tx.monto();

            if (comprador != 0) {
                montoMedioUltimoBloque += monto;
                int saldoAnterior = listaUsuarios[comprador - 1].getSaldo();
                int nuevoSaldo = saldoAnterior - monto;
                Usuario compradorActualizado = listaUsuarios[comprador - 1];
                compradorActualizado.actualizarSaldo(nuevoSaldo);
                heapUsuarios.actualizar(comprador - 1, compradorActualizado);
                listaUsuarios[comprador - 1] = compradorActualizado;
            }

            // vendedor
            int saldoAnterior = listaUsuarios[vendedor - 1].getSaldo();
            int nuevoSaldo = saldoAnterior + monto;
            Usuario vendedorActualizado = listaUsuarios[vendedor - 1];
            vendedorActualizado.actualizarSaldo(nuevoSaldo);
            heapUsuarios.actualizar(vendedor - 1, vendedorActualizado);
            listaUsuarios[vendedor - 1] = vendedorActualizado;

        }
        cadena.agregarAtras(heapTransacciones);

    }

    public Transaccion txMayorValorUltimoBloque() { // O(1)
        return heapTransacciones.obtener(0);
    }

    public Transaccion[] txUltimoBloque() { // O(n)
        heapArray<Transaccion> ultimo = cadena.ultimo();
        Transaccion[] ultimaOrdenadaSinHack = new Transaccion[ultimo.capacidad()];
        Transaccion[] ultimaOrdenadaHack = new Transaccion[ultimo.cantidadElementos()];
        for (int i = 0; i < ultimaOrdenadaSinHack.length; i++) {
            int idTransaccion = ultimo.obtener(i).ID();
            ultimaOrdenadaSinHack[idTransaccion] = ultimo.obtener(i);
        }
        int j = 0;
        if (ultimo.cantidadElementos() < ultimo.capacidad()) {
            for (int i = 0; i < ultimaOrdenadaSinHack.length; i++) {
                if (ultimaOrdenadaSinHack[i] != null) {
                    ultimaOrdenadaHack[j] = ultimaOrdenadaSinHack[i];
                    j++;
                }
            }
        }

        return ultimo.cantidadElementos() < ultimo.capacidad() ? ultimaOrdenadaHack : ultimaOrdenadaSinHack;
    }

    public int maximoTenedor() { // O(1)
        Usuario maximoTenedor = heapUsuarios.obtener(0);
        return maximoTenedor.getId();
    }

    public double montoMedioUltimoBloque() {// O(1)
        int transaccionesSinCreacion = heapTransacciones.cantidadElementos() - 1;

        return transaccionesSinCreacion == 0 ? 0
                : montoMedioUltimoBloque / transaccionesSinCreacion;
    }

    public void hackearTx() {// O(log n + logP )
        Transaccion txMaxima = heapTransacciones.eliminarMaximo();
        int compradorHackeado = txMaxima.id_comprador();
        int vendedorHackeado = txMaxima.id_vendedor();
        int montoHackeado = txMaxima.monto();

        // devolverle la plata al comprador
        if (compradorHackeado != 0) {
            montoMedioUltimoBloque -= montoHackeado;
            int saldoAnterior = listaUsuarios[compradorHackeado - 1].getSaldo();
            int nuevoSaldo = saldoAnterior + montoHackeado;
            Usuario compradorActualizado = listaUsuarios[compradorHackeado - 1];
            compradorActualizado.actualizarSaldo(nuevoSaldo);
            heapUsuarios.actualizar(compradorHackeado - 1, compradorActualizado);
            listaUsuarios[compradorHackeado - 1] = compradorActualizado;
        }

        // sacarle la plata al vendedor
        int saldoAnterior = listaUsuarios[vendedorHackeado - 1].getSaldo();
        int nuevoSaldo = saldoAnterior - montoHackeado;
        Usuario vendedorActualizado = listaUsuarios[vendedorHackeado - 1];
        vendedorActualizado.actualizarSaldo(nuevoSaldo);
        heapUsuarios.actualizar(vendedorHackeado - 1, vendedorActualizado);
        listaUsuarios[vendedorHackeado - 1] = vendedorActualizado;

    }
}
