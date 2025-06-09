package aed;

public class heapArray<T extends Comparable<T>> {
    private T[] heap;
    private int capacidad;
    private int tamaño;

    public heapArray(int capacidad) {
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.heap = (T[]) new Comparable[capacidad];
    }

    private int indexHijoIzquierdo(int i) {
        return (2 * i) + 1;
    }

    private int indexHijoDerecho(int i) {
        return (2 * i) + 2;
    }

    private int indexPadre(int i) {
        return (i - 1) / 2;
    }

    private boolean tieneHijoIzquierdo(int i) {
        return indexHijoIzquierdo(i) < tamaño;
    }

    private boolean tieneHijoDerecho(int i) {
        return indexHijoDerecho(i) < tamaño;
    }

    private boolean tienePadre(int i) {
        return indexPadre(i) >= 0;
    }

    private T hijoIzquierdo(int i) {
        return heap[indexHijoIzquierdo(i)];
    }

    private T hijoDerecho(int i) {
        return heap[indexHijoDerecho(i)];
    }

    private T padre(int i) {
        return heap[indexPadre(i)];
    }

    public T obtener(int i) {
        return heap[i];
    }

    public int cantidadElementos() {
        return tamaño;
    }

    public void actualizar(int id, T valor) {
        if (id != 0) {
            Integer valorActual = (Integer) heap[id - 1];
            Integer valorSuma = (Integer) valor;
            heap[id - 1] = (T) Integer.valueOf(valorActual + valorSuma);
            heapifyUp();
            heapifyDown();
        }

    }

    private void aumentarCapacidad() {
        T[] nuevoHeap = (T[]) new Comparable[this.capacidad * 2];
        int i = 0;
        while (i < tamaño) {
            nuevoHeap[i] = this.heap[i];
            i++;
        }
        this.heap = nuevoHeap;
        this.capacidad = this.capacidad * 2;
    }

    public void insertar(T valor) {
        if (capacidad == tamaño) {
            aumentarCapacidad();

        }
        heap[tamaño] = valor;
        tamaño++;
        heapifyUp();
    }

    public void eliminarMaximo() {
        heap[0] = heap[tamaño - 1];
        tamaño--;
        heapifyDown();
    }

    private void cambiar(int i, int j) {
        T cambiarT = heap[i];
        heap[i] = heap[j];
        heap[j] = cambiarT;
    }

    private void heapifyUp() {
        int index = this.tamaño - 1;
        while (index > 0 && (padre(index).compareTo(heap[index]) < 0)) {
            cambiar(index, indexPadre(index));
            index = indexPadre(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (tieneHijoIzquierdo(index)) {
            int indexHijoMayor = indexHijoIzquierdo(index);
            if (tieneHijoDerecho(index) && hijoDerecho(index).compareTo(hijoIzquierdo(index)) > 0) {
                indexHijoMayor = indexHijoDerecho(index);
            }

            if (heap[index].compareTo(heap[indexHijoMayor]) < 0) {
                cambiar(index, indexHijoMayor);
            }
            index = indexHijoMayor;

        }
    }
}
