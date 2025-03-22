import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestOneElementDict {
    private Dict<String, Integer> diccionario;

    @BeforeEach
    void setUp() {
        diccionario = new Dict<>(); // Reinicializa el diccionario antes de cada prueba
        diccionario.put("A", 1); // Añade un elemento al diccionario para las pruebas
    }

    @Test
    void testNotEmptyDict() {
        assertFalse(diccionario.isEmpty()); // Verifica que el diccionario no esté vacío
    }

    @Test
    void testSize() {
        int size = diccionario.size();
        assertEquals(1, size); // Verifica que el tamaño del diccionario sea 1
    }

    @Test
    void testGet() {
        assertEquals(1, diccionario.get("A")); // Verifica que el valor asociado a "A" sea 1
        assertNull(diccionario.get("B")); // Verifica que una clave no existente devuelva null
    }

    @Test
    void testPut() {
        diccionario.put("B", 2); // Añade un nuevo elemento
        assertEquals(2, diccionario.size()); // Verifica que el tamaño sea 2
        assertEquals(2, diccionario.get("B")); // Verifica que el valor se haya añadido correctamente
    }

    @Test
    void testContainsKey() {
        assertTrue(diccionario.containsKey("A")); // Verifica que la clave "A" exista en el diccionario
        assertFalse(diccionario.containsKey("B")); // Verifica que una clave no existente no esté en el diccionario
    }

    @Test
    void testRemove() {
        diccionario.remove("A"); // Elimina el elemento con clave "A"
        assertFalse(diccionario.containsKey("A")); // Verifica que la clave "A" ya no exista
        assertTrue(diccionario.isEmpty()); // Verifica que el diccionario esté vacío después de la eliminación
    }

    @Test
    void testPutOverwrite() {
        diccionario.put("A", 2); // Sobrescribe el valor asociado a "A"
        assertEquals(2, diccionario.get("A"), "El valor no se ha actualizado correctamente"); // Verifica que el valor se haya actualizado correctamente
        assertEquals(1, diccionario.size(), "El tamaño del diccionario no debería cambiar"); // Verifica que el tamaño del diccionario siga siendo 1
    }

    @Test
    void testRemoveNonExistingKey() {
        diccionario.remove("B"); // Intenta eliminar una clave que no existe
        assertEquals(1, diccionario.size()); // Verifica que el tamaño no haya cambiado
        assertTrue(diccionario.containsKey("A")); // Verifica que la clave "A" siga existiendo
    }



    @Test
    void testEntrySet() {
        Dict<String, Integer>.Node[] entries = diccionario.entrySet();
        assertEquals(1, entries.length); // Verifica que haya un solo elemento
        assertEquals("A", entries[0].key); // Verifica la clave
        assertEquals(1, entries[0].value); // Verifica el valor
    }

    @Test
    void testKeys() {
        String[] keys = diccionario.keys();
        assertEquals(1, keys.length); // Verifica que haya un solo elemento
        assertEquals("A", keys[0]); // Verifica la clave
    }

    @Test
    void testValues() {
        Integer[] values = diccionario.values();
        assertEquals(1, values.length); // Verifica que haya un solo elemento
        assertEquals(1, values[0]); // Verifica el valor
    }

    @Test
    void testToStringDict() {
        assertEquals("{A: 1}", diccionario.toString());
    }

}