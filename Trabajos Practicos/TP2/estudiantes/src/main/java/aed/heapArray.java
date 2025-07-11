package aed;

public class heapArray<T extends Comparable<T>> {
    private T[] heap;
    private int[] ids; // ids[pos] = id del elemento en esa posición del heap
    private int[] handles; // handles[id] = posición en el heap del elemento con ese id
    private int capacidad;
    private int tamaño;

    public heapArray(int capacidad) {
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.heap = (T[]) new Comparable[capacidad];
        this.handles = new int[capacidad];
        this.ids = new int[capacidad];
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

    public void actualizar(int id, T nuevoValor) {
        int posicion = handles[id];
        heap[posicion] = nuevoValor;
        siftUpDesde(posicion);
        siftDownDesde(posicion);
    }

    public void insertarHandle(int id, T valor) {
        heap[tamaño] = valor;
        handles[id] = tamaño;
        ids[tamaño] = id;
        tamaño++;
    }

    public int[] devolverHandles() {
        return handles;
    }

    public void heapify() {
        // agregamos el metodo heapify siguiendo el algoritmo de Floyd
        for (int i = (tamaño / 2) - 1; i >= 0; i--) {
            siftDownDesde(i);
        }
    }

    public T eliminarMaximo() {
        T maximo = heap[0];
        int idMaximo = ids[0];

        if (tamaño > 0) {
            tamaño--;
            if (tamaño == 0) {
                heap = (T[]) new Comparable[0];

            } else {
                heap[0] = heap[tamaño];
                ids[0] = ids[tamaño];
                handles[ids[0]] = 0;
                heap[tamaño] = null;
                ids[tamaño] = -1;
                handles[idMaximo] = -1;
                siftDown();
            }

        }

        return maximo;
    }

    private void cambiar(int i, int j) {
        T cambiarT = heap[i];
        heap[i] = heap[j];
        heap[j] = cambiarT;

        /*
         * int cambiarHandle = handles[i];====}
         * handles[i] = handles[j];===========} esto estaba mal
         * handles[j] = cambiarHandle;========}
         */

        handles[ids[i]] = j;
        handles[ids[j]] = i;

        int cambiarids = ids[i];
        ids[i] = ids[j];
        ids[j] = cambiarids;
    }

    private void siftUp() {
        int index = this.tamaño - 1;
        while (index > 0 && (padre(index).compareTo(heap[index]) < 0)) {
            cambiar(index, indexPadre(index));
            index = indexPadre(index);
        }
    }

    private void siftDown() {
        int index = 0;
        while (tieneHijoIzquierdo(index)) {
            int indexHijoMayor = indexHijoIzquierdo(index);
            if (tieneHijoDerecho(index) && hijoDerecho(index).compareTo(hijoIzquierdo(index)) > 0) {
                indexHijoMayor = indexHijoDerecho(index);
            }

            if (heap[index].compareTo(heap[indexHijoMayor]) < 0) {
                cambiar(index, indexHijoMayor);
                index = indexHijoMayor;

            } else {
                break;
            }

        }
    }

    private void siftUpDesde(int index) {
        while (index > 0 && padre(index).compareTo(heap[index]) < 0) {
            cambiar(index, indexPadre(index));
            index = indexPadre(index);
        }
    }

    private void siftDownDesde(int index) {
        while (tieneHijoIzquierdo(index)) {
            int indexHijoMayor = indexHijoIzquierdo(index);

            if (tieneHijoDerecho(index) &&
                    hijoDerecho(index).compareTo(hijoIzquierdo(index)) > 0) {
                indexHijoMayor = indexHijoDerecho(index);
            }

            if (heap[index].compareTo(heap[indexHijoMayor]) >= 0) {
                break;
            }

            cambiar(index, indexHijoMayor);
            index = indexHijoMayor;
        }
    }
}
