import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

public class TestEmptyDict {
    private Dict diccionario;
    @BeforeEach
    void setUp() {
        diccionario = new Dict();
    }

    @Test
   void testSize() {
        int size = diccionario.size();
        assertEquals(0, size);
    }

    @Test
    public void testContainsKey(){
        assertEquals(false, diccionario.containsKey("A"));
    }


    @Test
    public void testPut(){
        diccionario.put("A", 1);
        diccionario.put("B", 2);
        assertEquals(2, diccionario.size());
    }

    @Test
    public void testRemove(){
        diccionario.remove("A");
        assertEquals(1, diccionario.size());
    }

}
