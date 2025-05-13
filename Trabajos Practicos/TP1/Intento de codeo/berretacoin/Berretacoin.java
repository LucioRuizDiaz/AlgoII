package berretacoin;
import java.util.*;

class Transaccion {
    int id;
    int comprador;
    int vendedor;
    int monto;

    public Transaccion(int id, int comprador, int vendedor, int monto) {
        this.id = id;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.monto = monto;
    }

    public boolean esTransaccionCreacion(Set<Integer> recibieron) {
        return comprador == 0 && vendedor != 0 && !recibieron.contains(vendedor) && monto == 1;
    }

    public boolean esTransaccionValida() {
        return id >= 0 && comprador > 0 && vendedor > 0 && monto > 0 && comprador != vendedor;
    }
}

class Bloque {
    int id;
    List<Transaccion> transacciones;

    public Bloque(int id, List<Transaccion> transacciones) {
        this.id = id;
        this.transacciones = new ArrayList<>(transacciones);
    }

    public int sumaMontos() {
        return transacciones.stream().mapToInt(t -> t.monto).sum();
    }
}

public class Berretacoin {

    private List<Bloque> cadena;
    private Map<Integer, Integer> montosUsuarios;
    private List<Integer> montosTransacciones;
    private List<Integer> montosPorBloque;
    private Set<Integer> recibieron;
    private int totalCreadas;

    public Berretacoin() {
        cadena = new ArrayList<>();
        montosUsuarios = new HashMap<>();
        montosTransacciones = new ArrayList<>();
        montosPorBloque = new ArrayList<>();
        recibieron = new HashSet<>();
        totalCreadas = 0;
    }

    public boolean agregarBloque(List<Transaccion> transacciones) {
        if (transacciones.size() > 50)
            return false;

        // Validación y aplicación de transacciones
        for (int i = 0; i < transacciones.size(); i++) {
            Transaccion t = transacciones.get(i);
            if (i == 0 && totalCreadas < 3000) {
                if (!t.esTransaccionCreacion(recibieron))
                    return false;
                recibieron.add(t.vendedor);
                montosUsuarios.put(t.vendedor, montosUsuarios.getOrDefault(t.vendedor, 0) + t.monto);
                totalCreadas++;
            } else {
                if (!t.esTransaccionValida())
                    return false;
                int saldo = montosUsuarios.getOrDefault(t.comprador, 0);
                if (saldo < t.monto)
                    return false;
                montosUsuarios.put(t.comprador, saldo - t.monto);
                montosUsuarios.put(t.vendedor, montosUsuarios.getOrDefault(t.vendedor, 0) + t.monto);
                montosTransacciones.add(t.monto);
            }
        }
        Bloque bloque = new Bloque(cadena.size(), transacciones);
        cadena.add(bloque);
        montosPorBloque.add(bloque.sumaMontos());
        return true;
    }

    public List<Integer> maximosTenedores() {
        int max = montosUsuarios.values().stream().max(Integer::compare).orElse(0);
        List<Integer> resultado = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : montosUsuarios.entrySet()) {
            if (entry.getValue() == max) {
                resultado.add(entry.getKey());
            }
        }
        return resultado;
    }

    public double montoMedio() {
        if (montosTransacciones.isEmpty())
            return 0;
        return montosTransacciones.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public List<Integer> cotizacionAPesos(List<Integer> cotizaciones) {
        List<Integer> resultado = new ArrayList<>();
        for (int i = 0; i < cadena.size(); i++) {
            int montoBloque = montosPorBloque.get(i);
            int cotizacion = cotizaciones.get(i);
            resultado.add(montoBloque * cotizacion);
        }
        return resultado;
    }

    // Métodos auxiliares para testing
    public int cantidadBloques() {
        return cadena.size();
    }

    public int saldoUsuario(int id) {
        return montosUsuarios.getOrDefault(id, 0);
    }
}
