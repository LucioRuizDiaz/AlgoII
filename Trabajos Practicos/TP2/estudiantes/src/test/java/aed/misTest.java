package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class misTest {
    private Berretacoin berretacoin;
    private Transaccion[] transacciones;
    private Transaccion[] transacciones2;
    private Transaccion[] transacciones3;

    // Helper class para trackear saldos de usuarios
    private class SaldoTracker {
        private Map<Integer, Integer> saldos;

        public SaldoTracker(int usuarios) {
            saldos = new HashMap<>();
            for (int i = 1; i <= usuarios; i++) {
                saldos.put(i, 0);
            }
        }

        public void aplicarTransaccion(Transaccion tx) {
            if (tx.id_comprador() == 0) {
                saldos.put(tx.id_vendedor(), saldos.get(tx.id_vendedor()) + tx.monto());
            } else {
                saldos.put(tx.id_comprador(), saldos.get(tx.id_comprador()) - tx.monto());
                saldos.put(tx.id_vendedor(), saldos.get(tx.id_vendedor()) + tx.monto());
            }
        }

        public int getSaldo(int usuario) {
            return saldos.get(usuario);
        }

        public boolean puedeGastar(int usuario, int monto) {
            return usuario == 0 || getSaldo(usuario) >= monto;
        }

        public int getMaximoTenedor() {
            int maxSaldo = -1;
            int maxUsuario = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Integer> entry : saldos.entrySet()) {
                int usuario = entry.getKey();
                int saldo = entry.getValue();

                if (saldo > maxSaldo || (saldo == maxSaldo && usuario < maxUsuario)) {
                    maxSaldo = saldo;
                    maxUsuario = usuario;
                }
            }
            return maxUsuario;
        }

        public void revertirTransaccion(Transaccion tx) {
            if (tx.id_comprador() == 0) {
                saldos.put(tx.id_vendedor(), saldos.get(tx.id_vendedor()) - tx.monto());
            } else {
                saldos.put(tx.id_comprador(), saldos.get(tx.id_comprador()) + tx.monto());
                saldos.put(tx.id_vendedor(), saldos.get(tx.id_vendedor()) - tx.monto());
            }
        }
    }

@BeforeEach
    void setUp() {
        berretacoin = new Berretacoin(10);}

@Test
    public void soloCreacion() {
        Transaccion[] transaccion1 = new Transaccion[] {new Transaccion(0, 0, 1, 5)};
        Transaccion[] transaccion2 = new Transaccion[] {new Transaccion(0, 0, 2, 3)};
        Transaccion[] transaccion3 = new Transaccion[] {new Transaccion(0, 0, 3, 7)};
        
        berretacoin.agregarBloque(transaccion1);
        berretacoin.agregarBloque(transaccion2);
        berretacoin.agregarBloque(transaccion3);
        
        assertEquals(3, berretacoin.maximoTenedor());
        assertEquals(0, berretacoin.montoMedioUltimoBloque());
        assertEquals(new Transaccion(0, 0, 3, 7), berretacoin.txMayorValorUltimoBloque());
    }

@Test
    public void empates() {
        Transaccion[] transaccion1 = new Transaccion[]{new Transaccion(0, 0, 1, 5)};
        Transaccion[] transaccion2 = new Transaccion[]{new Transaccion(0, 0, 2, 5)};
        Transaccion[] transaccion3 = new Transaccion[]{new Transaccion(0, 0, 3, 5)};

        berretacoin.agregarBloque(transaccion1);
        berretacoin.agregarBloque(transaccion2);
        berretacoin.agregarBloque(transaccion3);
        
        assertEquals(1, berretacoin.maximoTenedor());
    }

@Test
    public void empateEnTransaccionesConDiferentesIDs() {
        Transaccion[] transaccion1 = new Transaccion[]{new Transaccion(0, 0, 1, 10)};
        Transaccion[] transaccion2 = new Transaccion[]{new Transaccion(0, 0, 2, 10)};
        Transaccion[] bloque = new Transaccion[]{new Transaccion(0, 0, 3, 10),
                                                 new Transaccion (1, 1, 4, 10),
                                                 new Transaccion (2, 2, 5, 10)};
        berretacoin.agregarBloque(transaccion1);
        berretacoin.agregarBloque(transaccion2);
        berretacoin.agregarBloque(bloque);
        
        assertEquals(new Transaccion(2, 2, 5, 10), berretacoin.txMayorValorUltimoBloque());
    }

@Test
    public void hackeoFuncionaBien() {
        Transaccion[] bloque1 = {new Transaccion(0, 0, 1, 10),
                                 new Transaccion(1, 1, 2, 3)};
                                 
        Transaccion[] bloque2 = {new Transaccion(0, 0, 3, 20)};
        
        berretacoin.agregarBloque(bloque1);
        berretacoin.agregarBloque(bloque2);
        
        assertEquals(3, berretacoin.maximoTenedor());
        
        berretacoin.hackearTx();

        assertTrue(berretacoin.maximoTenedor() == 1);
        assertEquals(0, berretacoin.txUltimoBloque().length);
    }
 }
