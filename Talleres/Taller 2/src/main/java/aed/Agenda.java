package aed;

public class Agenda {

    private Fecha fecha;

    public Agenda(Fecha fechaActual) {
        this.fecha = fechaActual;
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        // Implementar
    }

    @Override
    public String toString() {
        // Implementar
        return "";
    }

    public void incrementarDia() {
        // Implementar
    }

    public Fecha fechaActual() {
        Fecha fechaActual = this.fecha;
        return fechaActual;
    }

}
