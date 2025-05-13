package berretacoin;
import javax.swing.*;
//import java.awt.*;
import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;

public class BerretacoinGUI extends JFrame {
    private Berretacoin berretacoin;
    private JTextArea consola;

    public BerretacoinGUI() {
        super("Berretacoin GUI");
        this.berretacoin = new Berretacoin();

        setLayout(new BorderLayout());

        consola = new JTextArea();
        consola.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consola);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(0, 1));

        JButton btnCrear = new JButton("Agregar Bloque de Creación");
        btnCrear.addActionListener(e -> agregarBloqueCreacion());

        JButton btnTransferencia = new JButton("Agregar Bloque de Transferencia");
        btnTransferencia.addActionListener(e -> agregarBloqueTransferencia());

        JButton btnMaximos = new JButton("Maximos Tenedores");
        btnMaximos.addActionListener(e -> mostrarMaximosTenedores());

        JButton btnPromedio = new JButton("Monto Medio");
        btnPromedio.addActionListener(e -> mostrarMontoMedio());

        JButton btnCotizacion = new JButton("Cotizacion a Pesos");
        btnCotizacion.addActionListener(e -> mostrarCotizacionAPesos());

        panelBotones.add(btnCrear);
        panelBotones.add(btnTransferencia);
        panelBotones.add(btnMaximos);
        panelBotones.add(btnPromedio);
        panelBotones.add(btnCotizacion);

        add(panelBotones, BorderLayout.EAST);

        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void agregarBloqueCreacion() {
        String input = JOptionPane.showInputDialog(this, "ID del vendedor:");
        try {
            int vendedor = Integer.parseInt(input);
            Transaccion t = new Transaccion(0, 0, vendedor, 1);
            if (berretacoin.agregarBloque(List.of(t))) {
                consola.append("Bloque de creación agregado exitosamente.\n");
            } else {
                consola.append("Error: no se pudo agregar el bloque de creación.\n");
            }
        } catch (NumberFormatException e) {
            consola.append("Entrada inválida.\n");
        }
    }

    private void agregarBloqueTransferencia() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "ID de la transacción:"));
            int comprador = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del comprador:"));
            int vendedor = Integer.parseInt(JOptionPane.showInputDialog(this, "ID del vendedor:"));
            int monto = Integer.parseInt(JOptionPane.showInputDialog(this, "Monto:"));

            Transaccion t = new Transaccion(id, comprador, vendedor, monto);
            if (berretacoin.agregarBloque(List.of(t))) {
                consola.append("Bloque de transferencia agregado exitosamente.\n");
            } else {
                consola.append("Error: no se pudo agregar el bloque de transferencia.\n");
            }
        } catch (NumberFormatException e) {
            consola.append("Entrada inválida.\n");
        }
    }

    private void mostrarMaximosTenedores() {
        List<Integer> max = berretacoin.maximosTenedores();
        consola.append("Maximos tenedores: " + max + "\n");
    }

    private void mostrarMontoMedio() {
        double promedio = berretacoin.montoMedio();
        consola.append("Monto medio de transacciones: " + promedio + "\n");
    }

    private void mostrarCotizacionAPesos() {
        String input = JOptionPane.showInputDialog(this, "Ingrese cotizaciones separadas por coma (ej: 10,20):");
        try {
            String[] partes = input.split(",");
            List<Integer> cotizaciones = new ArrayList<>();
            for (String parte : partes) {
                cotizaciones.add(Integer.parseInt(parte.trim()));
            }
            List<Integer> resultado = berretacoin.cotizacionAPesos(cotizaciones);
            consola.append("Cotización a pesos: " + resultado + "\n");
        } catch (Exception e) {
            consola.append("Entrada inválida.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BerretacoinGUI::new);
    }
}
