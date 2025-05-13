package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo cabeza;
    private Nodo cola;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        Nodo(T v) {
            valor = v;
        }

    }

    public ListaEnlazada() {
        cabeza = null;
        cola = null;
    }

    public int longitud() {
        Nodo i = cabeza;
        int longitud = 0;
        while (i != null) {
            longitud += 1;
            i = i.siguiente;
        }
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.siguiente = cabeza;
        nuevo.anterior = null;
        cabeza = nuevo;

    }

    public void agregarAtras(T elem) {
        if (cabeza == null) {
            agregarAdelante(elem);
        } else {
            Nodo nuevo = new Nodo(elem);
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.anterior = actual;
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
        Nodo actual = cabeza;
        Nodo previo = cabeza;
        for (int j = 0; j < i; j++) {
            previo = actual;
            actual = actual.siguiente;
        }
        if (i == 0) {
            cabeza = actual.siguiente;
        } else {
            previo.siguiente = actual.siguiente;
        }
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = cabeza;
        for (int j = 0; j < indice; j++) {
            actual = actual.siguiente;
        }
        actual.valor = elem;

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
        // Completar atributos privados

        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public boolean hayAnterior() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T anterior() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        throw new UnsupportedOperationException("No implementada aun");
    }

}
