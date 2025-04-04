package aed;

class ArregloRedimensionableDeRecordatorios {

    private Recordatorio[] listaRecordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        this.listaRecordatorios = new Recordatorio[0];
    }

    public int longitud() {
        return this.listaRecordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {

        Recordatorio[] nueva_lista = new Recordatorio[this.listaRecordatorios.length + 1];
        for (int j = 0; j < this.listaRecordatorios.length; j++) {
            nueva_lista[j] = listaRecordatorios[j];
        }

        nueva_lista[nueva_lista.length - 1] = new Recordatorio(i.mensaje(), i.fecha(), i.horario());
        this.listaRecordatorios = nueva_lista.clone();
    }

    public Recordatorio obtener(int i) {
        return listaRecordatorios[i];
    }

    public void quitarAtras() {

        Recordatorio[] nueva_lista = new Recordatorio[this.listaRecordatorios.length - 1];
        for (int i = 0; i < this.listaRecordatorios.length - 1; i++) {
            nueva_lista[i] = listaRecordatorios[i];
        }
        this.listaRecordatorios = nueva_lista.clone();
    }

    public void modificarPosicion(int indice, Recordatorio valor) {

        Recordatorio[] nueva_lista = new Recordatorio[this.listaRecordatorios.length];
        for (int i = 0; i < this.listaRecordatorios.length; i++) {
            if (i == indice) {
                nueva_lista[i] = valor;
            } else {
                nueva_lista[i] = listaRecordatorios[i];
            }

        }
        this.listaRecordatorios = nueva_lista.clone();
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        Recordatorio[] nueva_lista = new Recordatorio[vector.longitud()];
        for (int i = 0; i < vector.longitud(); i++) {
            nueva_lista[i] = vector.obtener(i);
        }
        this.listaRecordatorios = nueva_lista.clone();

    }

    public ArregloRedimensionableDeRecordatorios copiar() {

        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios(this);
        return copia;
    }
}
