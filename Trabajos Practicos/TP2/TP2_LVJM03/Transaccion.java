package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    @Override
    public int compareTo(Transaccion otro) {
        int comparacion = 0;
        if (this.monto > otro.monto) {
            comparacion = 1;
        } else {
            if (this.monto == otro.monto) {
                if (this.id > otro.id) {
                    comparacion = 1;
                }
                if (this.id < otro.id) {
                    comparacion = -1;
                }
                if (this.id == otro.id) {
                    comparacion = 0;
                }
            } else {
                if (this.monto < otro.monto) {
                    comparacion = -1;
                }
            }
        }
        return comparacion;
    }

    @Override
    public boolean equals(Object otro) {
        Transaccion otroTransaccion = (Transaccion) otro;
        return this.id == otroTransaccion.id &&
                this.id_comprador == otroTransaccion.id_comprador &&
                this.id_vendedor == otroTransaccion.id_vendedor &&
                this.monto == otroTransaccion.monto;
    }

    public int monto() {
        return monto;
    }

    public int ID() {
        return id;
    }

    public int id_comprador() {
        return id_comprador;
    }

    public int id_vendedor() {
        return id_vendedor;
    }
}