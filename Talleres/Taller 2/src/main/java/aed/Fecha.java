package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int d, int m) {
        this.dia = d;
        this.mes = m;
    }

    public Fecha(Fecha fecha) {
        this(fecha.dia(), fecha.mes());
    }

    public Integer dia() {
        return dia;
    }

    public Integer mes() {
        return mes;
    }

    @Override
    public String toString() {
        return dia + "/" + mes;
    }

    @Override
    public boolean equals(Object otra) {
        boolean otroEsNull = (otra == null);
        boolean claseDistinta = otra.getClass() != this.getClass();

        if (otroEsNull || claseDistinta)
            return false;

        else {
            Fecha otraFecha = (Fecha) otra;
            return this.dia() == otraFecha.dia() && this.mes() == otraFecha.mes();
        }
    }

    public void incrementarDia() {
        if (dia == diasEnMes(mes)) {
            dia = 1;
            if (mes == 12)
                mes = 1;
            else
                mes += 1;
        } else {
            dia += 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
