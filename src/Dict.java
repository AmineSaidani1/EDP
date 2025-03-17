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

    //TODO: que modificar el código en put para sobrescribir el valor si la clave ya existe.
    public void put(K key, V value) {
        if (key == null) {
            mensaje(key);
            return;
        }
        int index = hash(key);
        Node newNode = new Node(key, value);
        Node previous = null;
        Node current = (Node) table[index];

        while (current != null) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            table[index] = newNode;
        } else{
            previous.next = newNode;
        }
        size++;
        if ((double) size / capacity > 0.8) {
            reSize();
        }
    }



    public V get(K key) {
        if (key == null) {
            mensaje(key);
            return null;
        }
        int index = hash(key);
        Node current = (Node) table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        mensaje2(key);
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

    public String toStringKeys() {
        StringBuilder result = new StringBuilder("{ ");
        Object[] keyArray = this.keys();
        for (int i = 0; i < keyArray.length; i++) {
            result.append(String.valueOf(keyArray[i]));

            if (i < keyArray.length - 1) {
                result.append(", ");
            }
        }
        result.append(" }");
        return result.toString();
    }

    public String toStringValues() {
        StringBuilder result = new StringBuilder("{ ");
        Object[] valueArray = this.values();
        for (int i = 0; i < valueArray.length; i++) {
            result.append(String.valueOf(valueArray[i]));

            if (i < valueArray.length - 1) {
                result.append(", ");
            } 
        }
        result.append(" }");
        return result.toString();
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

//TODO: hay que modificar el codigo en put para