package aed;

public class heapArray<T extends Comparable<T>> {
    private T[] heap;
    private int cantidad;
    private int tamaño;

    public heapArray(int cantidad) {
        this.cantidad = cantidad;
        this.tamaño = 0;
        this.heap = (T[]) new Comparable[cantidad];
    }

    private void asegurarseCapacidad() {
        if (tamaño == cantidad) {
            cantidad = cantidad * 2;
        }
    }

}