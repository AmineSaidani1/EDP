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
    private final HashMap<K, V> map;

    public Dict() {
        this.map = new HashMap<>();
    }
    public void put(K key, V value) {}

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

    public int size() {}

    public boolean isEmpty() {}

}
