import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestTwoElementsDict {
    private Dict<String, Integer> diccionario;

    @BeforeEach
    void setUp() {
        diccionario = new Dict<>(); // Reinicializa el diccionario antes de cada prueba
        diccionario.put("A", 1); // Añade el primer elemento al diccionario
        diccionario.put("B", 2); // Añade el segundo elemento al diccionario
    }

    @Test
    void testNotEmptyDict() {
        assertFalse(diccionario.isEmpty()); // Verifica que el diccionario no esté vacío
    }

    @Test
    void testSize() {
        int size = diccionario.size();
        assertEquals(2, size); // Verifica que el tamaño del diccionario sea 2
    }

    @Test
    void testGet() {
        assertEquals(1, diccionario.get("A")); // Verifica que el valor asociado a "A" sea 1
        assertEquals(2, diccionario.get("B")); // Verifica que el valor asociado a "B" sea 2
        assertNull(diccionario.get("C")); // Verifica que una clave no existente devuelva null
    }
    @Test
    void testPutNewKey() {
        diccionario.put("C", 3); // Añade un nuevo elemento
        assertEquals(3, diccionario.size()); // Verifica que el tamaño sea 3
        assertEquals(3, diccionario.get("C")); // Verifica que el valor se haya añadido correctamente
    }

    @Test
    void testContainsKey() {
        assertTrue(diccionario.containsKey("A")); // Verifica que la clave "A" exista en el diccionario
        assertTrue(diccionario.containsKey("B")); // Verifica que la clave "B" exista en el diccionario
        assertFalse(diccionario.containsKey("C")); // Verifica que una clave no existente no esté en el diccionario
    }

    @Test
    void testRemove() {
        assertEquals(1, diccionario.remove("A")); // Elimina el elemento con clave "A" y verifica el valor devuelto
        assertFalse(diccionario.containsKey("A")); // Verifica que la clave "A" ya no exista
        assertTrue(diccionario.containsKey("B")); // Verifica que la clave "B" siga existiendo
        assertEquals(1, diccionario.size()); // Verifica que el tamaño del diccionario sea 1 después de la eliminación
    }

    @Test
    void testPutOverwrite() {
        diccionario.put("A", 10); // Sobrescribe el valor asociado a "A"
        diccionario.put("B", 20); // Sobrescribe el valor asociado a "B"
        assertEquals(10, diccionario.get("A"), "El valor de 'A' no se ha actualizado correctamente"); // Verifica que el valor de "A" se haya actualizado correctamente
        assertEquals(20, diccionario.get("B"), "El valor de 'B' no se ha actualizado correctamente"); // Verifica que el valor de "B" se haya actualizado correctamente
        assertEquals(2, diccionario.size(), "El tamaño del diccionario no debería cambiar"); // Verifica que el tamaño del diccionario siga siendo 2
    }

    @Test
    void testRemoveAllElements() {
        diccionario.remove("A"); // Elimina el primer elemento
        diccionario.remove("B"); // Elimina el segundo elemento
        assertTrue(diccionario.isEmpty()); // Verifica que el diccionario esté vacío
        assertEquals(0, diccionario.size()); // Verifica que el tamaño del diccionario sea 0
    }
    @Test
    void testRemoveNonExistingKey() {
        diccionario.remove("C"); // Intenta eliminar una clave que no existe
        assertEquals(2, diccionario.size()); // Verifica que el tamaño no haya cambiado
        assertTrue(diccionario.containsKey("A")); // Verifica que la clave "A" siga existiendo
        assertTrue(diccionario.containsKey("B")); // Verifica que la clave "B" siga existiendo
    }

    @Test
    void testEntrySet() {
        Dict<String, Integer>.Node[] entries = diccionario.entrySet();
        assertEquals(2, entries.length); // Verifica que haya dos elementos

        boolean foundA = false, foundB = false;
        for (Dict<String, Integer>.Node entry : entries) {
            if (entry.key.equals("A") && entry.value == 1) {
                foundA = true;
            } else if (entry.key.equals("B") && entry.value == 2) {
                foundB = true;
            }
        }
        assertTrue(foundA && foundB); // Verifica que ambos elementos estén presentes
    }

    @Test
    void testKeys() {
        String[] keys = diccionario.keys();
        assertEquals(2, keys.length); // Verifica que haya dos elementos

        boolean foundA = false, foundB = false;
        for (String key : keys) {
            if (key.equals("A")) {
                foundA = true;
            } else if (key.equals("B")) {
                foundB = true;
            }
        }
        assertTrue(foundA && foundB); // Verifica que ambas claves estén presentes
    }

    @Test
    void testValues() {
        Integer[] values = diccionario.values();
        assertEquals(2, values.length); // Verifica que haya dos elementos

        boolean found1 = false, found2 = false;
        for (Integer value : values) {
            if (value == 1) {
                found1 = true;
            } else if (value == 2) {
                found2 = true;
            }
        }
        assertTrue(found1 && found2); // Verifica que ambos valores estén presentes
    }

    @Test
    void testToStringDict() {
        assertEquals("{A: 1, B: 2}", diccionario.toString());
    }

}