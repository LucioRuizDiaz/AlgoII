package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int h, int m) {
        this.hora = h;
        this.minutos = m;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override
    public String toString() {
        return hora + ":" + minutos;
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otroEsNull || claseDistinta)
            return false;

        else {
            Horario otroHorario = (Horario) otro;
            return this.hora() == otroHorario.hora() && this.minutos == otroHorario.minutos();
        }
    }

}
