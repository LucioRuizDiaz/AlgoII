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

        Nodo(T v) {
            valor = v;
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
        throw new UnsupportedOperationException("No implementada aun");
    }

    public T maximo() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void insertar(T elem) {
        Nodo hoja = new Nodo(elem);
        if (raiz == null) {
            raiz = hoja;
        } else {
            if (pertenece(elem)) {
                int comparacion = elem.compareTo(raiz.valor);
                if (comparacion == 0) {
                    raiz = hoja;
                } else {
                    if (comparacion > 0) {
                        if (raiz.derecho == null) {
                            raiz.derecho = hoja;
                        } else {
                            insertar(elem);
                        }
                    } else {
                        if (raiz.izquierdo == null) {
                            raiz.izquierdo = hoja;
                        } else {
                            insertar(elem);
                        }
                    }
                }
            } else {
                int comparacion = elem.compareTo(raiz.valor);
                if (comparacion == 0) {
                    raiz = hoja;
                } else {
                    if (comparacion > 0) {
                        if (raiz.derecho == null) {
                            raiz.derecho = hoja;
                        } else {
                            insertar(elem);

                        }
                    } else {
                        if (raiz.izquierdo == null) {
                            raiz.izquierdo = hoja;
                        } else {
                            raiz = raiz.izquierdo;
                            insertar(elem);

                        }
                    }
                }
            }
        }

    }

    /*
     * public void insertarIter(Nodo actual, T elem) {
     * int comparacion = elem.compareTo(actual.valor);
     * if (comparacion == 0) {
     * 
     * }
     * if (comparacion > 0) {
     * actual = actual.derecho;
     * insertar(elem);
     * } else {
     * actual = actual.izquierdo;
     * insertar(elem);
     * }
     * }
     */
    public boolean pertenece(T elem) {
        Nodo actual = raiz;
        return perteneceIter(actual, elem);
    }

    private boolean perteneceIter(Nodo actual, T elem) {
        if (actual == null) {
            return false;
        }
        int comparacion = elem.compareTo(raiz.valor);
        if (comparacion == 0) {
            return true;
        } else {
            if (comparacion > 0) {
                actual = actual.derecho;
                return perteneceIter(actual, elem);
            } else {
                actual = actual.izquierdo;
                return perteneceIter(actual, elem);
            }

        }
    }

    public void eliminar(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
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
