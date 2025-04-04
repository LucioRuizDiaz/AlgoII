package aed;

public class Agenda {

    private Fecha fecha;
    private ArregloRedimensionableDeRecordatorios arreglo;

    public Agenda(Fecha fechaActual) {
        this.fecha = new Fecha(fechaActual);
        this.arreglo = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.arreglo.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String arregloString = "";
        for (int i = 0; i < this.arreglo.longitud(); i++) {
            if (this.fechaActual().equals(this.arreglo.obtener(i).fecha())) {
                arregloString = arregloString + this.arreglo.obtener(i).toString() + "\n";
            }
        }
        return this.fechaActual() + "\n=====\n" + arregloString;
    }

    public void incrementarDia() {
        Fecha fecha_actual = this.fechaActual();
        fecha_actual.incrementarDia();
    }

    public Fecha fechaActual() {
        Fecha fechaActual = this.fecha;
        return fechaActual;
    }

}
