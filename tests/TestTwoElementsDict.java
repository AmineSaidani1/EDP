import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestTwoElementsDict {
    private Dict<String, Integer> diccionario;
    @BeforeEach
    void setUp() {
        diccionario = new Dict();
    }

    @Test
    void testPutAndGet() {
        diccionario.put("uno", 1);
        diccionario.put("dos", 2);

        assertEquals(1, diccionario.get("uno"));
        assertEquals(2, diccionario.get("dos"));
    }

    @Test
    void testContainsKey() {
        diccionario.put("tres", 3);

        assertTrue(diccionario.containsKey("tres"));
        assertFalse(diccionario.containsKey("cuatro"));
    }

    @Test
    void testRemove() {
        diccionario.put("cuatro", 4);
        diccionario.remove("cuatro");

        assertFalse(diccionario.containsKey("cuatro"));
        assertEquals(0, diccionario.size());
    }

    @Test
    void testSize() {
        assertEquals(0, diccionario.size());

        diccionario.put("cinco", 5);
        diccionario.put("seis", 6);

        assertEquals(2, diccionario.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(diccionario.isEmpty());

        diccionario.put("siete", 7);

        assertFalse(diccionario.isEmpty());
    }

    @Test
    void testGetNonExistentKey() {
        assertNull(diccionario.get("ocho"));
    }

    @Test
    void testPutOverwrite() {
        diccionario.put("nueve", 9);
        diccionario.put("nueve", 99);

        assertEquals(99, diccionario.get("nueve"));
    }
}