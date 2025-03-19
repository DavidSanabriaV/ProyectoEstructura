package com.mycompany.proyectotorreshanoidfja;

public class PilaDinamica {

    private Nodo cima;
    private int size;

    public PilaDinamica() {
        this.cima = null;
        this.size = 0;
    }

    //Metodo para hacer push de los datos
    public void push(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
        size++;
    }

    //Metodo para eliminar los datos
    public int pop() {
        if (cima == null) {
            System.out.println("La pila esta vacia");
            return -1;
        }
        int dato = cima.dato;
        cima = cima.siguiente;
        size--;
        return dato;
    }

    //Metodo para mostrar la cima 
    public int peek() {
        if (cima == null) {
            return Integer.MAX_VALUE;
        } else {
            return cima.dato;
        }
    }

    //Metodo para ver si esta vacia la pila
    public boolean isEmpty() {
        return cima == null;
    }

    //Metodo por si es necesario exponer toda la pila 
    public void mostrar() {
        if (cima == null) {
            System.out.println(" La pila esta vacia.");
            return;
        }
        Nodo actual = cima;
        System.out.println(" Contenido de la pila: ");
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    public int size() {
        return size;
    }
}
