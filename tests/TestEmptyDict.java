import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmptyDict {
    private Dict<String, Integer> diccionario;
    @BeforeEach
    void setUp() {
        diccionario = new Dict();
    }

    @Test
    void testPutAndGet() {


        assertEquals(1, diccionario.get("uno"));
        assertEquals(2, diccionario.get("dos"));
    }
}
