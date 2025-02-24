/**
 * TODO
 * crear y desarrollar los metodos:
 * constructor Dict()
 * void put(K key, V value)
 * V get(K key)
 * void remove(K key)
 * boolean containsKey(K key)
 * int size()
 * boolean isEmpty()
 */

import java.util.*;

public class Dict<K, V> {
    private class Node{

        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] Table;
    private static final int Capacity = 12;
    private int Size;

// Teoria --> Posición = H(clave) mod Ltabla(tamaño de la tabla)
    public Dict() {
        this.Table = new Node[Capacity];
        this.Size = 0;
    }
    // Usar Math.abs(ya que tal vez puedan llegar a dar valores negativos)
    private int Hash(K key){
        return Math.abs(key.hashCode() % Capacity);
    }
    //Si la H(clave) ya existe, lo que actualizamos es su valor, sin tener que crear un nuevo nodo.
    //Si la H(clave) no existe, se crea un nuevo nodo añadiendo la clave.
    //Además de eso, hay que comprobar si se supera el 80% de la capacidad.
    public void put(K key, V value) {
        int Index = Hash(key);
        Node Current = Table[Index];
        while (Current != null) {
            if (Current.key.equals(key)) {
                Current.value = value;
            }
        }
    }

    public V get(K key) {
    }

    public V remove(K key) {
        int index = hash(key);                      // Calculamos la posición en la tabla
        Node current = Table[index];                // Apuntamos al primer nodo de la lista en esa posición
        Node prev = null;                           // Variable para rastrear el nodo anterior

        while (current != null) {                   // Recorremos la lista en ese índice
            if (current.key.equals(key)) {          // Si encontramos la clave
                if (prev == null) {                 // Caso especial: el nodo a eliminar es el primero de la lista
                    Table[index] = current.next;    // Saltamos al siguiente nodo
                } else {
                    prev.next = current.next;       // Saltamos el nodo actual eliminándolo de la lista
                }
                Size--;                             // Reducimos el tamaño del diccionario
                return current.value;               // Devolvemos el valor eliminado
            }
            prev = current;                         // Mantenemos el nodo anterior
            current = current.next;                 // Avanzamos al siguiente nodo
        }
        return null;                                // No se encontró la clave
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int getSize() {
        return Size;
    }

    public void reSize() {
        Node[] oldTable = Table;
        Table = new Node[Capacity * 2];
        Node oldHead = head;
        Node prev = null;
        Node current = null;
        Size = 0;

        while (current != null) {
            addElement(current.key, current.value);
            current = current.nextInOrder;
        }

    }

    private void addElement(K key, V value) {

    }

    public boolean isEmpty() {


    }

}
