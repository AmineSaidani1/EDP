public class Dict<K, V> {
    private static final double LOAD_FACTOR = 0.8; // Se redimensiona si superamos el 80% de ocupación
    private static final int INITIAL_CAPACITY = 12; // Tamaño inicial de la tabla

    private Object[] table; // Tabla hash que almacena listas de nodos
    private int capacity; // Capacidad actual de la tabla
    private int size; // Número de elementos en el diccionario

    public Dict() {
        this.capacity = INITIAL_CAPACITY;
        this.table = new Object[capacity];
        this.size = 0;
    }

    private void mensaje(K key) {
        System.err.println("La clave no puede ser nula");
    }
    private void mensaje2(K key) {
        System.err.println("Clave:" + key + " no encontrada");
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public void put(K key, V value) {
        if ((double) size / capacity > LOAD_FACTOR) {
            reSize();
        }

        int index = hash(key);
        Node current = (Node) table[index];

        if (current == null) {
            table[index] = new Node(key, value);
            size++;
            return;
        }

        Node prev = null;
        while (current != null) {
            if (current.key == key || (current.key != null && current.key.equals(key))) {
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }

        prev.next = new Node(key, value);
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        Node current = (Node) table[index];

        while (current != null) {
            if (current.key == key || (current.key != null && current.key.equals(key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Node current = (Node) table[index];
        Node prev = null;

        while (current != null) {
            if (current.key == key || (current.key != null && current.key.equals(key))) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void reSize() {
        int newCapacity = capacity * 2;
        Object[] newTable = new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            Node current = (Node) table[i];
            while (current != null) {
                int newIndex = (current.key == null) ? 0 : Math.abs(current.key.hashCode() % newCapacity);

                Node newNode = new Node(current.key, current.value);
                newNode.next = (Node) newTable[newIndex];
                newTable[newIndex] = newNode;

                current = current.next;
            }
        }

        this.capacity = newCapacity;
        this.table = newTable;
    }

    public K[] keys() {
        // Usamos Array.newInstance() para crear un arreglo genérico del tipo K
        K[] keyArray = (K[]) java.lang.reflect.Array.newInstance(table.getClass().getComponentType(), size);
        int count = 0;

        for (int i = 0; i < capacity; i++) {
            Node current = (Node) table[i];
            while (current != null) {
                keyArray[count++] = current.key;
                current = current.next;
            }
        }
        return keyArray;
    }


    public V[] values() {
        // Usamos Array.newInstance() para crear un arreglo genérico del tipo V
        V[] valueArray = (V[]) java.lang.reflect.Array.newInstance(table.getClass().getComponentType(), size);
        int count = 0;

        for (int i = 0; i < capacity; i++) {
            Node current = (Node) table[i];
            while (current != null) {
                valueArray[count++] = current.value;
                current = current.next;
            }
        }
        return valueArray;
    }


    public Node[] entrySet() {
        Object[] entries = new Object[size];
        int count = 0;

        for (int i = 0; i < capacity; i++) {
            Node current = (Node) table[i];
            while (current != null) {
                entries[count++] = current;
                current = current.next;
            }
        }
        Node[] result = (Node[]) entries;
        return result;
    }

    public class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}


// Redimensionamiento Dinámico
// Se implementa en el método reSize().
// - Si la carga (size / capacity) supera el 80%, se duplica el tamaño de la
// tabla.
// - Se reinsertan todos los elementos en la nueva tabla usando la nueva
// capacidad.

// ¿Por qué?
// - Mejora el rendimiento evitando listas muy largas en un solo índice.
// - Simula el comportamiento de los dict en Python, que crecen dinámicamente.

// Cadenas Fundidas (Listas Enlazadas)
// Se implementan en la clase Node, usada en put(), get() y remove().
// - Cuando dos claves tienen el mismo índice (hash(key)) se almacenan en una
// lista enlazada.
// - Cada índice en table[] puede apuntar a una cadena de nodos (Node).

// ¿Por qué?
// - Es una técnica de manejo de colisiones eficiente cuando hay pocas
// colisiones.

// Dispersión (Hashing)
// Se usa en el método hash(K key).
// - Calcula Math.abs(key.hashCode() % capacity).
// - Asigna una posición en la tabla basándose en la clave.

// ¿Por qué?
// - Reduce el acceso secuencial, mejorando el tiempo de búsqueda.

// cuando se elimina un elemento se cambia el size?
// añadir toString para claves y valores