package aed;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo cabeza;
    private Nodo ultimo;
    private int tamaño;

    public class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T v) {
            valor = v;
            siguiente = null;
            anterior = null;
        }

    }

    public ListaEnlazada() {
        cabeza = null;
        ultimo = null;
        tamaño = 0;
    }

    public int longitud() {
        return tamaño;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamaño++;
    }

    public void agregarAtras(T elem) {
        if (cabeza == null) {
            agregarAdelante(elem);
        } else {
            Nodo nuevo = new Nodo(elem);
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
            tamaño++;
        }
    }

    public Nodo agregarAtrasConNodo(T elem) {
        if (cabeza == null) {
            Nodo nuevo = new Nodo(elem);
            cabeza = nuevo;
            ultimo = nuevo;
            tamaño++;
            return nuevo;
        } else {
            Nodo nuevo = new Nodo(elem);
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
            tamaño++;
            return nuevo;
        }
    }

    public T obtener(int i) {
        Nodo actual = cabeza;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        if (i < 0 || i >= tamaño)
            return;
        Nodo actual = cabeza;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        eliminar(actual);
    }

    public void eliminar(Nodo nodo) {
        if (nodo == null)
            return;
        if (nodo.anterior != null) {
            nodo.anterior.siguiente = nodo.siguiente;
        } else {
            cabeza = nodo.siguiente;
        }
        if (nodo.siguiente != null) {
            nodo.siguiente.anterior = nodo.anterior;
        } else {
            ultimo = nodo.anterior;
        }
        tamaño--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = cabeza;
        for (int j = 0; j < indice; j++) {
            actual = actual.siguiente;
        }
        actual.valor = elem;

    }

    public T ultimo() {
        return ultimo.valor;
    }

    public void cambiarUltimo(T nuevoValor) {
        if (ultimo != null) {
            ultimo.valor = nuevoValor;
        } else {
            agregarAtras(nuevoValor);
        }
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.cabeza;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.siguiente;
        }
    }

    @Override
    public String toString() {
        Nodo actual = cabeza;
        String lista = "[";
        while (actual != null) {
            if (actual.siguiente != null) {
                lista += actual.valor + ", ";
            } else {
                lista += actual.valor + "]";
            }
            actual = actual.siguiente;
        }

        return lista;
    }

    private class ListaIterador implements Iterador<T> {
        private int dedito;

        ListaIterador() {
            dedito = 0;
        }

        public boolean haySiguiente() {
            return dedito != longitud();
        }

        public boolean hayAnterior() {
            return dedito + longitud() != longitud();
        }

        public T siguiente() {
            int i = dedito;
            dedito++;
            return obtener(i);
        }

        public T anterior() {
            dedito--;
            return obtener(dedito);
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
