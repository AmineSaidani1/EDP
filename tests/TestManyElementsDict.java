import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestManyElementsDict {
    private Dict<String, Integer> diccionario;

    @BeforeEach
    void setUp() {
        diccionario = new Dict<>(); // Reinicializa el diccionario antes de cada prueba
        // Añade muchos elementos al diccionario
        for (int i = 0; i < 100; i++) {
            diccionario.put("Key" + i, i); // Claves: "Key0", "Key1", ..., "Key99"
        }
    }

    @Test
    void testNotEmptyDict() {
        assertFalse(diccionario.isEmpty()); // Verifica que el diccionario no esté vacío
    }

    @Test
    void testSize() {
        int size = diccionario.size();
        assertEquals(100, size); // Verifica que el tamaño del diccionario sea 100
    }

    @Test
    void testGet() {
        for (int i = 0; i < 100; i++) {
            assertEquals(i, diccionario.get("Key" + i)); // Verifica que cada valor sea correcto
        }
        assertNull(diccionario.get("InvalidKey")); // Verifica que una clave no existente devuelva null
    }

    @Test
    void testContainsKey() {
        for (int i = 0; i < 100; i++) {
            assertTrue(diccionario.containsKey("Key" + i)); // Verifica que cada clave exista
        }
        assertFalse(diccionario.containsKey("InvalidKey")); // Verifica que una clave no existente no esté en el diccionario
    }

    @Test
    void testRemove() {
        diccionario.remove("Key50"); // Elimina un elemento específico
        assertFalse(diccionario.containsKey("Key50")); // Verifica que la clave eliminada ya no exista
        assertEquals(99, diccionario.size()); // Verifica que el tamaño del diccionario se reduzca a 99
    }

    @Test
    void testPutOverwrite() {
        diccionario.put("Key50", 1000); // Sobrescribe el valor asociado a "Key50"
        assertEquals(1000, diccionario.get("Key50")); // Verifica que el valor se haya actualizado correctamente
        assertEquals(100, diccionario.size()); // Verifica que el tamaño del diccionario no cambie
    }

    @Test
    void testRemoveAllElements() {
        for (int i = 0; i < 100; i++) {
            diccionario.remove("Key" + i); // Elimina todos los elementos uno por uno
        }
        assertTrue(diccionario.isEmpty()); // Verifica que el diccionario esté vacío
        assertEquals(0, diccionario.size()); // Verifica que el tamaño del diccionario sea 0
    }

    @Test
    void testAddMoreElements() {
        for (int i = 100; i < 200; i++) {
            diccionario.put("Key" + i, i); // Añade más elementos al diccionario
        }
        assertEquals(200, diccionario.size()); // Verifica que el tamaño del diccionario sea 200
        for (int i = 0; i < 200; i++) {
            assertTrue(diccionario.containsKey("Key" + i)); // Verifica que todas las claves existan
        }
    }
}