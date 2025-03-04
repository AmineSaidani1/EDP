public class Main {
    public static void main(String[] args) {
        // Crear un diccionario de prueba
        Dict<String, Integer> dict = new Dict<>();

        // Insertar valores
        System.out.println("Añadiendo valores...");
        dict.put("Uno", 1);
        dict.put("Dos", 2);
        dict.put("Tres", 3);
        dict.put("Cuatro", 4);
        dict.put("Cinco", 5);

        // Obtener valores
        System.out.println("Valor de 'Dos': " + dict.get("Dos")); // Debe imprimir 2
        System.out.println("Valor de 'Cuatro': " + dict.get("Cuatro")); // Debe imprimir 4
        System.out.println("Valor de 'Seis' (no existe): " + dict.get("Seis")); // Debe imprimir null

        // Verificar si existen claves
        System.out.println("¿El diccionario contiene 'Tres'? " + dict.containsKey("Tres")); // true
        System.out.println("¿El diccionario contiene 'Siete'? " + dict.containsKey("Siete")); // false

        // Eliminar valores
        System.out.println("Eliminando 'Dos': " + dict.remove("Dos")); // Debe imprimir 2
        System.out.println("Intentando obtener 'Dos' después de eliminar: " + dict.get("Dos")); // null

        // Tamaño del diccionario
        System.out.println("Tamaño actual del diccionario: " + dict.size()); // Debe imprimir 4

        // Insertar más valores para forzar redimensionamiento
        for (int i = 6; i <= 15; i++) {
            dict.put("Clave" + i, i);
        }

        // Verificar tamaño después del redimensionamiento
        System.out.println("Tamaño del diccionario después de añadir más elementos: " + dict.size());

        // Listar claves y valores
        System.out.println("Claves en el diccionario: " + dict.keys());
        System.out.println("Valores en el diccionario: " + dict.values());

        // Verificar si está vacío
        System.out.println("¿Está vacío el diccionario? " + dict.isEmpty()); // false

        // Eliminar todos los elementos
        for (Object key : dict.keys()) {
            dict.remove((String) key);
        }

        System.out.println("¿Está vacío después de eliminar todo? " + dict.isEmpty()); // true
    }
}
