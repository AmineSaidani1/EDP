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
        diccionario.put(null, 1);
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

}

//TODO probar los metodos entryset, keys y values