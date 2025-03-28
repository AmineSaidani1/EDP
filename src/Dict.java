public class Dict<K, V> {
    private static final double LOAD_FACTOR = 0.8; // Se redimensiona si superamos el 80% de ocupación
    private static final int INITIAL_CAPACITY = 12; // Tamaño inicial de la tabla

    private Object[] table; // Tabla hash que almacena listas de nodos
    private int capacity; // Capacidad actual de la tabla
    private static int size; // Número de elementos en el diccionario
    private InsertionOrderList<K> insertionOrder;

    public Dict() {
        this.capacity = INITIAL_CAPACITY;
        this.table = new Object[capacity];
        this.size = 0;
        this.insertionOrder = new InsertionOrderList<>();
    }

    // Clase estática interna para la lista enlazada
    private static class InsertionOrderList<K> {
        private Node<K> head; // Primer nodo de la lista
        private Node<K> tail; // Último nodo de la lista

        public InsertionOrderList() {
            this.head = null;
            this.tail = null;
        }

        // Añadir una clave al final de la lista
        public void add(K key) {
            Node<K> newNode = new Node<>(key);
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Eliminar una clave de la lista
        public void remove(K key) {
            Node<K> current = head;
            Node<K> previous = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    if (previous == null) {
                        head = current.next; // Eliminar el primer nodo
                    } else {
                        previous.next = current.next; // Eliminar un nodo intermedio o el último
                    }
                    if (current == tail) {
                        tail = previous; // Actualizar tail si se elimina el último nodo
                    }
                    return;
                }
                previous = current;
                current = current.next;
            }
        }

        // Obtener todas las claves en orden de inserción
        public K[] getKeys(K[] array) {
            Node<K> current = head;
            int index = 0;
            while (current != null) {
                array[index++] = current.key; // Llenar el array con las claves
                current = current.next;
            }
            return array;
        }

        // Métodoo adicional para obtener el tipo de la primera clave
        public K[] getKeys() {
            if (head == null) {
                return (K[]) new Object[0]; // Devolver un array vacío si no hay claves
            }
            K[] keyArray = (K[]) java.lang.reflect.Array.newInstance(head.key.getClass(), size);
            return getKeys(keyArray);
        }

        // Clase estática interna para los nodos de la lista enlazada
        private static class Node<K> {
            K key;
            Node<K> next;

            public Node(K key) {
                this.key = key;
                this.next = null;
            }
        }
    }

    private void mensaje(K key) {
        throw new NullPointerException("Key cannot be null");
    }
    private void mensaje2(K key) {
        System.err.println("Clave:" + key + " no encontrada");
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

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
            if (current.key.equals(key)) {
                current.value = value; // Overwrite the value
                return;
            }
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            table[index] = newNode;
        } else {
            previous.next = newNode;
        }
        insertionOrder.add(key); // Añadir la clave a la lista enlazada
        size++;
        if ((double) size / capacity > LOAD_FACTOR) {
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
                insertionOrder.remove(key); // Eliminar la clave de la lista enlazada
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
        if (size == 0) {
            return (K[]) new Object[0]; // Devolver un array vacío si no hay claves
        }
        // Crear un array del tipo correcto usando reflection
        K[] keyArray = (K[]) java.lang.reflect.Array.newInstance(insertionOrder.getKeys()[0].getClass(), size);
        return insertionOrder.getKeys(keyArray); // Llenar el array con las claves
    }


    public V[] values() {
        if (size == 0) {
            return (V[]) new Object[0]; // Devolver un array vacío si no hay valores
        }
        // Crear un array del tipo correcto usando reflection
        V[] valueArray = (V[]) java.lang.reflect.Array.newInstance(get(insertionOrder.getKeys()[0]).getClass(), size);
        K[] keys = keys(); // Obtener las claves en orden de inserción
        for (int i = 0; i < size; i++) {
            valueArray[i] = get(keys[i]); // Llenar el array con los valores
        }
        return valueArray;
    }


    public Node[] entrySet() {
        Node[] entries = (Node[]) java.lang.reflect.Array.newInstance(Node.class, size);
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

    // Métodoo toString para imprimir el diccionario en orden de inserción
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        boolean first = true;

        K[] keys = keys(); // Obtener las claves en orden de inserción
        for (K key : keys) {
            if (!first) {
                result.append(", ");
            }
            result.append(key).append(": ").append(get(key));
            first = false;
        }
        result.append("}");
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

//TODO: guardar el orden de inserción de elementos.