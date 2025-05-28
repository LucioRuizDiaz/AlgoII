package aed;

import java.util.*;

import org.w3c.dom.Node;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
    // Agregar atributos privados del Conjunto

    private class Nodo {
        T valor;
        Nodo derecho;
        Nodo izquierdo;
        Nodo padre;

        Nodo(T v) {
            valor = v;
            izquierdo = null;
            derecho = null;
            padre = null;
        }

        private int cantidadDeDescendientes() {
            int descIzq = izquierdo == null ? 0 : 1 + izquierdo.cantidadDeDescendientes();
            int descDer = derecho == null ? 0 : 1 + derecho.cantidadDeDescendientes();
            return descDer + descIzq;
        }

    }

    public ABB() {
        raiz = null;
    }

    public int cardinal() {
        return raiz == null ? 0 : 1 + raiz.cantidadDeDescendientes();
    }

    public T minimo() {
        return minimoRecursivo(raiz);
    }

    private T minimoRecursivo(Nodo actual) {
        return actual.izquierdo == null ? actual.valor : minimoRecursivo(actual.izquierdo);
    }

    public T maximo() {
        return maximoRecursivo(raiz);
    }

    private T maximoRecursivo(Nodo actual) {
        return actual.derecho == null ? actual.valor : maximoRecursivo(actual.derecho);
    }

    public void insertar(T elem) {
        if (raiz == null) {
            raiz = new Nodo(elem);
        } else {
            insertarRecu(raiz, elem);
        }
    }

    private void insertarRecu(Nodo actual, T elem) {
        int comparacion = elem.compareTo(actual.valor);
        if (!pertenece(elem))
            if (comparacion < 0) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(elem);
                    actual.izquierdo.padre = actual;
                } else {
                    insertarRecu(actual.izquierdo, elem);
                }
            } else {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(elem);
                    actual.derecho.padre = actual;
                } else {
                    insertarRecu(actual.derecho, elem);
                }
            }
    }

    public boolean pertenece(T elem) {
        return perteneceRecu(raiz, elem);
    }

    private boolean perteneceRecu(Nodo actual, T elem) {
        if (actual == null) {
            return false;
        } else {
            int comparacion = elem.compareTo(actual.valor);
            if (comparacion == 0) {
                return true;
            }
            if (comparacion > 0)
                return perteneceRecu(actual.derecho, elem);
            else
                return perteneceRecu(actual.izquierdo, elem);
        }
    }

    public void eliminar(T elem) {
        raiz = eliminarRecu(raiz, elem);
    }

    private Nodo eliminarRecu(Nodo actual, T elem) {
        if (actual == null)
            return null;
        int comparacion = elem.compareTo(actual.valor);
        if (comparacion == 0) {
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }
            actual.valor = minimoRecursivo(actual.derecho); // busca el minimo del subarbol derecho
            actual.derecho = eliminarRecu(actual.derecho, actual.valor); // lo elimina del arbol despues de copiarlo
                                                                         // para que no este repetido
        } else {
            if (comparacion < 0) {
                actual.izquierdo = eliminarRecu(actual.izquierdo, elem);
            } else {
                actual.derecho = eliminarRecu(actual.derecho, elem);
            }

        }

        return actual;

    }

    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo actual;

        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
