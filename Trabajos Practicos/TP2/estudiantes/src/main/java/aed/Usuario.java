package aed;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private int saldo;

    public Usuario(int id, int saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void actualizarSaldo(int monto) {
        this.saldo += monto;
    }

    @Override
    public int compareTo(Usuario otro) {
        int comparacion = 0;
        if (this.saldo > otro.saldo) {
            comparacion = 1;
        } else {
            if (this.saldo == otro.saldo) {
                if (this.id < otro.id) {
                    comparacion = 1;
                } else {
                    comparacion = -1;
                }
            }
            if (this.saldo < otro.saldo) {
                comparacion = -1;
            }

        }
        return comparacion;
    }

}
