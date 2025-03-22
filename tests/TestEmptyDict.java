import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class TestEmptyDict {
    private Dict<String, Integer> diccionario;

    @BeforeEach
    void setUp() {
        diccionario = new Dict<>(); // Reinicializa el diccionario antes de cada prueba
    }

    @Test
    void testEmptyDict() {
        assertTrue(diccionario.isEmpty());
    }

    @Test
    void testSize() {
        int size = diccionario.size();
        assertEquals(0, size);
    }



    @Test
    void testPut() {
        diccionario.put("A", 1);
        diccionario.put("B", 2);
        assertEquals(2, diccionario.size());
    }

    @Test
    void testPutNullKey() {
        assertThrows(NullPointerException.class, () -> diccionario.put(null, 1));
    }



    @Test
    void testGet() {
        diccionario.put("A", 1);
        diccionario.put("B", 2);
        assertEquals(1, diccionario.get("A"));
        assertEquals(2, diccionario.get("B"));
        assertNull(diccionario.get("C"));
    }

    @Test
    void testContainsKey() {
        assertFalse(diccionario.containsKey("A"));
        diccionario.put("A", 1);
        assertTrue(diccionario.containsKey("A"));
    }


    @Test
    void testRemove() {
        diccionario.put("A", 1);
        diccionario.remove("A");
        assertFalse(diccionario.containsKey("A"));
    }

    @Test
    void testEntrySet() {
        diccionario.put("A", 1);
        diccionario.put("B", 2);

        Dict<String, Integer>.Node[] entries = diccionario.entrySet();
        assertEquals(2, entries.length);

        // Verify the keys and values in the entries
        boolean foundA = false, foundB = false;
        for (Dict<String, Integer>.Node entry : entries) {
            if (entry.key.equals("A") && entry.value == 1) {
                foundA = true;
            } else if (entry.key.equals("B") && entry.value == 2) {
                foundB = true;
            }
        }
        assertTrue(foundA && foundB);
    }

    @Test
    void testKeys() {
        diccionario.put("A", 1);
        diccionario.put("B", 2);

        String[] keys = diccionario.keys();
        assertEquals(2, keys.length);

        // Verify the keys
        boolean foundA = false, foundB = false;
        for (String key : keys) {
            if (key.equals("A")) {
                foundA = true;
            } else if (key.equals("B")) {
                foundB = true;
            }
        }
        assertTrue(foundA && foundB);
    }

    @Test
    void testValues() {
        diccionario.put("A", 1);
        diccionario.put("B", 2);

        Integer[] values = diccionario.values();
        assertEquals(2, values.length);

        // Verify the values
        boolean found1 = false, found2 = false;
        for (Integer value : values) {
            if (value == 1) {
                found1 = true;
            } else if (value == 2) {
                found2 = true;
            }
        }
        assertTrue(found1 && found2);
    }

    @Test
    void testToStringKeys() {
        assertEquals("{}", diccionario.toStringKeys());
    }

    @Test
    void testToStringValues() {
        assertEquals("{}", diccionario.toStringValues());
    }

    @Test
    void testToStringDict() {
        assertEquals("{}", diccionario.toString());
    }

}

//TODO probar los metodos entryset, keys y values