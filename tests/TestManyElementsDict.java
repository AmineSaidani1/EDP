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
        assertEquals(1000, diccionario.get("Key50"), "El valor de 'Key50' no se ha actualizado correctamente"); // Verifica que el valor se haya actualizado correctamente
        assertEquals(100, diccionario.size(), "El tamaño del diccionario no debería cambiar"); // Verifica que el tamaño del diccionario no cambie
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

    @Test
    void testEntrySet() {
        Dict<String, Integer>.Node[] entries = diccionario.entrySet();
        assertEquals(100, entries.length); // Verifica que haya 100 elementos

        boolean[] found = new boolean[100]; // Array para verificar que todas las claves estén presentes
        for (Dict<String, Integer>.Node entry : entries) {
            int index = Integer.parseInt(entry.key.substring(3)); // Extrae el índice de la clave
            assertEquals(index, entry.value); // Verifica que el valor sea correcto
            found[index] = true; // Marca la clave como encontrada
        }

        // Verifica que todas las claves estén presentes
        for (boolean f : found) {
            assertTrue(f);
        }
    }

    @Test
    void testKeys() {
        String[] keys = diccionario.keys();
        assertEquals(100, keys.length); // Verifica que haya 100 elementos

        boolean[] found = new boolean[100]; // Array para verificar que todas las claves estén presentes
        for (String key : keys) {
            int index = Integer.parseInt(key.substring(3)); // Extrae el índice de la clave
            found[index] = true; // Marca la clave como encontrada
        }

        // Verifica que todas las claves estén presentes
        for (boolean f : found) {
            assertTrue(f);
        }
    }

    @Test
    void testValues() {
        Integer[] values = diccionario.values();
        assertEquals(100, values.length); // Verifica que haya 100 elementos

        boolean[] found = new boolean[100]; // Array para verificar que todos los valores estén presentes
        for (Integer value : values) {
            found[value] = true; // Marca el valor como encontrado
        }

        // Verifica que todos los valores estén presentes
        for (boolean f : found) {
            assertTrue(f);
        }
    }

    @Test
    void testToStringDict() {
        assertEquals("{Key0: 0, Key1: 1, Key2: 2, Key3: 3, Key4: 4, Key5: 5, Key6: 6, Key7: 7, Key8: 8, Key9: 9, Key10: 10, Key11: 11, Key12: 12, Key13: 13, Key14: 14, Key15: 15, Key16: 16, Key17: 17, Key18: 18, Key19: 19, Key20: 20, Key21: 21, Key22: 22, Key23: 23, Key24: 24, Key25: 25, Key26: 26, Key27: 27, Key28: 28, Key29: 29, Key30: 30, Key31: 31, Key32: 32, Key33: 33, Key34: 34, Key35: 35, Key36: 36, Key37: 37, Key38: 38, Key39: 39, Key40: 40, Key41: 41, Key42: 42, Key43: 43, Key44: 44, Key45: 45, Key46: 46, Key47: 47, Key48: 48, Key49: 49, Key50: 50, Key51: 51, Key52: 52, Key53: 53, Key54: 54, Key55: 55, Key56: 56, Key57: 57, Key58: 58, Key59: 59, Key60: 60, Key61: 61, Key62: 62, Key63: 63, Key64: 64, Key65: 65, Key66: 66, Key67: 67, Key68: 68, Key69: 69, Key70: 70, Key71: 71, Key72: 72, Key73: 73, Key74: 74, Key75: 75, Key76: 76, Key77: 77, Key78: 78, Key79: 79, Key80: 80, Key81: 81, Key82: 82, Key83: 83, Key84: 84, Key85: 85, Key86: 86, Key87: 87, Key88: 88, Key89: 89, Key90: 90, Key91: 91, Key92: 92, Key93: 93, Key94: 94, Key95: 95, Key96: 96, Key97: 97, Key98: 98, Key99: 99}", diccionario.toString());
    }
}