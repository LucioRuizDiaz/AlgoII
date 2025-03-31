package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int d, int m) {
        dia = d;
        mes = m;
    }

    public Fecha(Fecha fecha) {

    }

    public Integer dia() {
        int devolverDia = dia;
        return devolverDia;
    }

    public Integer mes() {
        int  devolverMes = mes;
        return devolverMes;
    }

    
    @Override
    public String toString() {
        return dia + "/" + mes;
    }

    


    @Override
    public boolean equals(Object otra) {
        boolean otroEsNull = (otra == null);
        boolean claseDistinta = otra.getClass() != this.getClass();
        
        if(otroEsNull || claseDistinta) return false;

        else{
            Fecha otraFecha = (Fecha) otra;
            return dia == otraFecha.dia() && mes == otraFecha.mes();
        }
    }

    public void incrementarDia() {
        if (dia == diasEnMes(mes)){
            dia = 1;
            if (mes == 12) mes = 1;
            else
            mes += 1;
        }
        else{
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
