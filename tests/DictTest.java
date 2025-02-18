import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictTest {
    @Test
    void test() {
        Dict d = new Dict();
        assertEquals("Hola Diccionario", d.Dict());
    }
}