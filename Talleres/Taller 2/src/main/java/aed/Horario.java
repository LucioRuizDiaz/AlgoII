package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int h, int m) {
        hora = h;
        minutos = m;
    }

    public int hora() {
        int devolverHora = hora;
        return devolverHora;
    }

    public int minutos() {
        int devolverMinutos = minutos;
        return devolverMinutos;
    }

    @Override
    public String toString() {
        return hora + ":" + minutos ;
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();
        
        if(otroEsNull || claseDistinta) return false;

        else{
            Horario otroHorario = (Horario) otro;
            return hora == otroHorario.hora() && minutos == otroHorario.minutos();
        }
    }

}
