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
    void testContainsKey() {
        assertTrue(diccionario.containsKey("A")); // Verifica que la clave "A" exista en el diccionario
        assertTrue(diccionario.containsKey("B")); // Verifica que la clave "B" exista en el diccionario
        assertFalse(diccionario.containsKey("C")); // Verifica que una clave no existente no esté en el diccionario
    }

    @Test
    void testRemove() {
        diccionario.remove("A"); // Elimina el elemento con clave "A"
        assertFalse(diccionario.containsKey("A")); // Verifica que la clave "A" ya no exista
        assertTrue(diccionario.containsKey("B")); // Verifica que la clave "B" siga existiendo
        assertEquals(1, diccionario.size()); // Verifica que el tamaño del diccionario sea 1 después de la eliminación
    }

    @Test
    void testPutOverwrite() {
        diccionario.put("A", 10); // Sobrescribe el valor asociado a "A"
        diccionario.put("B", 20); // Sobrescribe el valor asociado a "B"
        assertEquals(10, diccionario.get("A"), "el valor no se ha actualizado despues de sobrescribirlo"); // Verifica que el valor de "A" se haya actualizado correctamente
        assertEquals(20, diccionario.get("B"), "el valor no se ha actualizado despues de sobrescribirlo"); // Verifica que el valor de "B" se haya actualizado correctamente
        assertEquals(2, diccionario.size(), "el tamaño se ha cambiado al sobrescribir"); // Verifica que el tamaño del diccionario siga siendo 2
    }

    @Test
    void testRemoveAllElements() {
        diccionario.remove("A"); // Elimina el primer elemento
        diccionario.remove("B"); // Elimina el segundo elemento
        assertTrue(diccionario.isEmpty()); // Verifica que el diccionario esté vacío
        assertEquals(0, diccionario.size()); // Verifica que el tamaño del diccionario sea 0
    }
}