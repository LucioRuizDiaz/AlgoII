package aed;

public class heapArray<T extends Comparable<T>> {
    private T[] heap;
    private int[] handles; // handles[id] = posición en el heap del elemento con ese id
    private int[] ids; // ids[pos] = id del elemento en esa posición del heap
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

    public void actualizar(int id, T nuevoValor) {
        int posicion = handles[id];
        T valorPrevio = heap[posicion];
        heap[posicion] = nuevoValor;
        if (nuevoValor.compareTo(valorPrevio) > 0) {
            heapifyUpDesde(posicion);
        } else if (nuevoValor.compareTo(valorPrevio) < 0) {
            heapifyDownDesde(posicion);
        }

    }

    private void aumentarCapacidad() {
        int nuevaCapacidad = capacidad * 2;
        T[] nuevoHeap = (T[]) new Comparable[nuevaCapacidad];
        int[] nuevosHandles = new int[nuevaCapacidad];
        int[] nuevosIds = new int[nuevaCapacidad];

        for (int i = 0; i < tamaño; i++) {
            nuevoHeap[i] = heap[i];
            nuevosHandles[i] = handles[i];
            nuevosIds[i] = ids[i];
        }
        heap = nuevoHeap;
        handles = nuevosHandles;
        ids = nuevosIds;
        capacidad = nuevaCapacidad;
    }

    public void insertarHandle(int id, T valor) {
        if (capacidad == tamaño) {
            aumentarCapacidad();
        }
        heap[tamaño] = valor;
        handles[id] = tamaño;
        ids[tamaño] = id;
        tamaño++;
        heapifyUp();
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

        /*
         * int cambiarHandle = handles[i];====}
         * handles[i] = handles[j];===========} esto estaba mal
         * handles[j] = cambiarHandle;========}
         */
        // Actualizar handles: el elemento que estaba en posición i ahora está en j, y
        // viceversa
        handles[ids[i]] = j;
        handles[ids[j]] = i;
        int cambiarids = ids[i];
        ids[i] = ids[j];
        ids[j] = cambiarids;

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

    private void heapifyUpDesde(int index) {
        while (index > 0 && padre(index).compareTo(heap[index]) < 0) {
            cambiar(index, indexPadre(index));
            index = indexPadre(index);
        }
    }

    private void heapifyDownDesde(int index) {
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
