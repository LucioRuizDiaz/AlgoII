package berretacoin;
import java.util.*;

class Main {
    public static void main(String[] args) {
        Berretacoin bc = new Berretacoin();

        // Bloque de creación
        List<Transaccion> bloque1 = List.of(new Transaccion(0, 0, 1, 1));
        System.out.println("Bloque 1 agregado: " + bc.agregarBloque(bloque1));

        // Bloque de transferencia
        List<Transaccion> bloque2 = List.of(new Transaccion(1, 1, 2, 1));
        System.out.println("Bloque 2 agregado: " + bc.agregarBloque(bloque2));

        System.out.println("Cantidad de bloques: " + bc.cantidadBloques());
        System.out.println("Saldo usuario 1: " + bc.saldoUsuario(1));
        System.out.println("Saldo usuario 2: " + bc.saldoUsuario(2));
        System.out.println("Máximos tenedores: " + bc.maximosTenedores());
        System.out.println("Monto medio: " + bc.montoMedio());

        List<Integer> cotizaciones = List.of(100, 200);
        System.out.println("Cotización a pesos: " + bc.cotizacionAPesos(cotizaciones));
    }
}
