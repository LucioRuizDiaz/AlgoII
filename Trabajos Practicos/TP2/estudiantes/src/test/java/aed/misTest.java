package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class misTest {
    private Berretacoin berretacoin;

    // Helper class para trackear saldos de usuarios

    @BeforeEach
    void setUp() {
        berretacoin = new Berretacoin(10);
    }

    @Test
    public void soloCreacion() {
        Transaccion[] transaccion1 = new Transaccion[] { new Transaccion(0, 0, 1, 5) };
        Transaccion[] transaccion2 = new Transaccion[] { new Transaccion(0, 0, 2, 3) };
        Transaccion[] transaccion3 = new Transaccion[] { new Transaccion(0, 0, 3, 7) };

        berretacoin.agregarBloque(transaccion1);
        berretacoin.agregarBloque(transaccion2);
        berretacoin.agregarBloque(transaccion3);

        assertEquals(3, berretacoin.maximoTenedor());
        assertEquals(0, berretacoin.montoMedioUltimoBloque());
        assertEquals(new Transaccion(0, 0, 3, 7), berretacoin.txMayorValorUltimoBloque());
    }

    @Test
    public void empates() {
        Transaccion[] transaccion1 = new Transaccion[] { new Transaccion(0, 0, 1, 5) };
        Transaccion[] transaccion2 = new Transaccion[] { new Transaccion(0, 0, 2, 5) };
        Transaccion[] transaccion3 = new Transaccion[] { new Transaccion(0, 0, 3, 5) };

        berretacoin.agregarBloque(transaccion1);
        berretacoin.agregarBloque(transaccion2);
        berretacoin.agregarBloque(transaccion3);

        assertEquals(1, berretacoin.maximoTenedor());
    }

    @Test
    public void empateEnTransaccionesConDiferentesIDs() {
        Transaccion[] transaccion1 = new Transaccion[] { new Transaccion(0, 0, 1, 10) };
        Transaccion[] transaccion2 = new Transaccion[] { new Transaccion(0, 0, 2, 10) };
        Transaccion[] bloque = new Transaccion[] { new Transaccion(0, 0, 3, 10),
                new Transaccion(1, 1, 4, 10),
                new Transaccion(2, 2, 5, 10) };
        berretacoin.agregarBloque(transaccion1);
        berretacoin.agregarBloque(transaccion2);
        berretacoin.agregarBloque(bloque);

        assertEquals(new Transaccion(2, 2, 5, 10), berretacoin.txMayorValorUltimoBloque());
    }

    @Test
    public void hackeoFuncionaBien() {
        Transaccion[] bloque1 = { new Transaccion(0, 0, 1, 10),
                new Transaccion(1, 1, 2, 3) };

        Transaccion[] bloque2 = { new Transaccion(0, 0, 3, 20) };

        berretacoin.agregarBloque(bloque1);
        berretacoin.agregarBloque(bloque2);

        assertEquals(3, berretacoin.maximoTenedor());

        berretacoin.hackearTx();

        assertTrue(berretacoin.maximoTenedor() == 1);
        assertEquals(0, berretacoin.txUltimoBloque().length);
    }

    @Test
    public void hackeoRestauraSaldosCorrectamente() {
        Transaccion[] bloque = {
                new Transaccion(0, 0, 1, 100),
                new Transaccion(1, 1, 2, 50),
                new Transaccion(2, 2, 3, 30)
        };
        Transaccion[] bloque2 = {
                new Transaccion(0, 0, 3, 20),
                new Transaccion(1, 1, 3, 50),
                new Transaccion(2, 3, 4, 100)
        };
        berretacoin.agregarBloque(bloque);
        berretacoin.agregarBloque(bloque2);

        assertEquals(4, berretacoin.maximoTenedor());

        berretacoin.hackearTx();
        assertEquals(3, berretacoin.maximoTenedor());

        berretacoin.hackearTx();
        assertEquals(1, berretacoin.maximoTenedor());
    }

}
