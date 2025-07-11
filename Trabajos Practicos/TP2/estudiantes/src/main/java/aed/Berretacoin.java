package aed;

//P = cantidad de usuarios
//n = cantidad de transacciones

public class Berretacoin {
    private ListaEnlazada<heapArray<Transaccion>> cadena;
    private Usuario[] listaUsuarios;
    private heapArray<Usuario> heapUsuarios;
    private heapArray<Transaccion> heapTransacciones;
    private int montoTotalUltimoBloque;
    private int cantidadTransacciones;
    private ListaEnlazada<Integer> transaccionesValidas;
    private Object[] nodosTransacciones; // Array de nodos para acceso O(1)

    public Berretacoin(int n_usuarios) { // O(P)
        this.cadena = new ListaEnlazada<heapArray<Transaccion>>();
        this.heapUsuarios = new heapArray<>(n_usuarios);
        this.listaUsuarios = new Usuario[n_usuarios];
        for (int i = 0; i < n_usuarios; i++) { // O(P)
            Usuario usuario = new Usuario(i + 1, 0);
            heapUsuarios.insertarHandle(i, usuario);
            listaUsuarios[i] = usuario;
        }
        heapUsuarios.heapify();
    }

    public void agregarBloque(Transaccion[] transacciones) { // O(n * log(P))
        this.heapTransacciones = new heapArray<>(transacciones.length);
        this.montoTotalUltimoBloque = 0;
        this.cantidadTransacciones = 0;
        this.transaccionesValidas = new ListaEnlazada<Integer>();
        this.nodosTransacciones = new Object[transacciones.length];

        int i = 0;
        for (Transaccion tx : transacciones) { // O(n)
            heapTransacciones.insertarHandle(i, tx); // O(log(n))
            int comprador = tx.id_comprador();
            int vendedor = tx.id_vendedor();
            int monto = tx.monto();

            if (comprador != 0) {
                montoTotalUltimoBloque += monto;
                cantidadTransacciones += 1;
                actualizarSaldos(comprador, -monto); // O(log P)
            }
            // vendedor
            actualizarSaldos(vendedor, monto); // O(log P)

            // lista enlazada de transacciones
            nodosTransacciones[i] = transaccionesValidas.agregarAtrasConNodo(i);
            i++;

        }
        heapTransacciones.heapify();
        cadena.agregarAtras(heapTransacciones); // O(1)
    }

    public Transaccion txMayorValorUltimoBloque() { // O(1)
        return heapTransacciones.obtener(0);
    }

    public Transaccion[] txUltimoBloque() { // O(n)
        heapArray<Transaccion> ultimo = cadena.ultimo();
        Transaccion[] ultimaOrdenada = new Transaccion[transaccionesValidas.longitud()];
        int[] handles = ultimo.devolverHandles();

        /*
         * if (ultimo.cantidadElementos() > 0) {// si el heap esta vacio deja el array
         * vacio
         * int j = 0;
         * for (int i = 0; i < handles.length; i++) { // O(n)
         * if (handles[i] != -1) {// solo entra si el handle es valido y
         * ultimaOrdenada[j] = ultimo.obtener(handles[i]);
         * j++;
         * }
         * }
         * 
         * }
         */
        int j = 0;
        Iterador<Integer> iter = transaccionesValidas.iterador();
        while (iter.haySiguiente()) {
            int indice = iter.siguiente();
            ultimaOrdenada[j] = ultimo.obtener(handles[indice]);
            j++;
        }
        return ultimaOrdenada;
    }

    public int maximoTenedor() { // O(1)
        Usuario maximoTenedor = heapUsuarios.obtener(0);
        return maximoTenedor.getId();
    }

    public double montoMedioUltimoBloque() {// O(1)
        return cantidadTransacciones == 0 ? 0
                : montoTotalUltimoBloque / cantidadTransacciones;
    }

    public void hackearTx() {// O(log n + logP )
        Transaccion txMaxima = heapTransacciones.eliminarMaximo(); // O(log n)

        int compradorHackeado = txMaxima.id_comprador();
        int vendedorHackeado = txMaxima.id_vendedor();
        int montoHackeado = txMaxima.monto();
        int idHackeado = txMaxima.ID();

        if (nodosTransacciones[idHackeado] != null) {
            ListaEnlazada<Integer>.Nodo nodoAEliminar = (ListaEnlazada<Integer>.Nodo) nodosTransacciones[idHackeado];
            transaccionesValidas.eliminar(nodoAEliminar);
            nodosTransacciones[idHackeado] = null;
        }

        // devolverle la plata al comprador
        if (compradorHackeado != 0) {
            montoTotalUltimoBloque -= montoHackeado;
            cantidadTransacciones -= 1;
            actualizarSaldos(compradorHackeado, montoHackeado); // O(logP)

        }
        // sacarle la plata al vendedor
        actualizarSaldos(vendedorHackeado, -montoHackeado);// O(logP)

    }

    private void actualizarSaldos(int usuario, int monto) {// O(logP)
        int saldoAnterior = listaUsuarios[usuario - 1].getSaldo();
        int nuevoSaldo = saldoAnterior + monto;
        Usuario usuarioActualizado = listaUsuarios[usuario - 1];
        usuarioActualizado.actualizarSaldo(nuevoSaldo);
        heapUsuarios.actualizar(usuario - 1, usuarioActualizado); // O(log(P))
        listaUsuarios[usuario - 1] = usuarioActualizado;
    }
}
