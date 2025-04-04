package aed;

public class Recordatorio {
    private Fecha fecha;
    private Horario horario;
    private String mensaje;

    public Recordatorio(String m, Fecha f, Horario h) {
        fecha = f;
        this.fecha = new Fecha(fecha);
        this.horario = h;
        this.mensaje = m;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {
        return new Fecha(fecha.dia(), fecha.mes());
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return this.mensaje() + " @ " + this.fecha() + " " + this.horario();
    }

    @Override
    public boolean equals(Object otro) {

        boolean otroEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otroEsNull || claseDistinta)
            return false;

        else {
            Recordatorio otroRecordatorio = (Recordatorio) otro;
            boolean mismoMensaje = this.mensaje() == otroRecordatorio.mensaje();
            boolean mismoHorario = this.horario.equals(otroRecordatorio.horario());
            boolean mismaFecha = this.fecha.equals(otroRecordatorio.fecha());
            return mismoMensaje && mismoHorario && mismaFecha;
        }
    }
}
