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
        assertEquals(2, diccionario.get("A")); // Verifica que el valor se haya actualizado correctamente
        assertEquals(1, diccionario.size()); // Verifica que el tamaño del diccionario siga siendo 1
    }
}