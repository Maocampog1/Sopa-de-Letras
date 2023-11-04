package FunProgramación.SOPA;

import java.util.Scanner;

public class SopaReal {
    private static int pasosEnSopa = 0;

    public static boolean horizontalDirecto(char[][] matriz, String palabra, int fila, int columna) {
        int numColumnas = matriz[0].length;// Obtenemos el número de columnas en la matriz.
        int longitud = palabra.length(); // Calculamos la longitud de la palabra que estamos buscando.
        // Comprobamos si la posición inicial más la longitud de la palabra supera el
        // número de columnas.
        if (columna + longitud > numColumnas) {
            return false;// Si no cabe horizontalmente, devolvemos false.
        }

        // Cicl que recorre cada carácter en la palabra.
        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;// Contamos los pasos o comparaciones realizadas en la búsqueda.
            // Comparamos el carácter actual de la palabra con el carácter correspondiente
            // en la matriz.
            if (palabra.charAt(i) != matriz[fila][columna + i]) {
                return false;// Si encontramos una diferencia, devolvemos false.
            }
        }
        // Si el bucle se completa sin encontrar diferencias, devolvemos true, lo que
        // significa que la palabra se encontró en esa posición.
        return true;
    }

    public static boolean horizontalInvertida(char[][] matriz, String palabra, int fila, int columna) {
        int numColumnas = matriz[0].length;
        int longitud = palabra.length();
        // Comprobamos si la posición inicial menos la longitud de la palabra está fuera
        // de los límites de la matriz (menor que 0).
        if (columna - longitud < 0) {
            return false;// Si no cabe horizontalmente en sentido inverso, devolvemos false.
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            // Comparamos el carácter actual de la palabra con el carácter correspondiente
            // en la matriz en sentido inverso.
            if (palabra.charAt(i) != matriz[fila][columna - i]) {
                return false;// Si encontramos una diferencia, devolvemos false.

            }
        }
        return true;
    }

    public static boolean verticalDirecto(char[][] matriz, String palabra, int fila, int columna) {
        int numFilas = matriz.length;
        int longitud = palabra.length();

        // Verificar si la posición inicial más la longitud de la palabra supera el
        // número de filas.
        if (fila + longitud > numFilas) {
            return false;
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            if (palabra.charAt(i) != matriz[fila + i][columna]) {
                return false;
            }
        }
        return true;
    }

    public static boolean verticalInvertida(char[][] matriz, String palabra, int fila, int columna) {
        int numFilas = matriz.length;
        int longitud = palabra.length();

        // Verificar si la palabra no cabe en la columna invertida
        if (fila - longitud + 1 < 0) {
            return false;
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            // Comparar el carácter de la matriz (en dirección vertical hacia arriba) con el
            // carácter de la palabra.
            char letraSopa = matriz[fila - i][columna];
            char letraPalabra = palabra.charAt(i);

            if (letraSopa != letraPalabra) {
                return false;
            }
        }

        return true;
    }

    public static boolean diagonalIzquierdaDerechaDirecto(char[][] matriz, String palabra, int fila, int columna) {
        int numFilas = matriz.length;
        int numColumnas = matriz[0].length;
        int longitud = palabra.length();
        // Verificar si la palabra no cabe en la diagonal de izquierda a derecha hacia
        // abajo.
        if (fila + longitud > numFilas || columna + longitud > numColumnas) {
            return false;
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            // Comparar el carácter actual de la palabra con el carácter correspondiente en
            // la matriz en la dirección diagonal.
            if (palabra.charAt(i) != matriz[fila + i][columna + i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean diagonalDerechaIzquierdaDirecto(char[][] matriz, String palabra, int fila, int columna) {
        int numFilas = matriz.length;
        int longitud = palabra.length();

        if (fila + longitud > numFilas || columna - longitud < 0) {
            return false;
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            if (palabra.charAt(i) != matriz[fila + i][columna - i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean diagonalIzquierdaDerechaInvertida(char[][] matriz, String palabra, int fila,
            int columna) {
        int numColumnas = matriz[0].length;
        int longitud = palabra.length();

        if (fila - longitud < 0 || columna + longitud > numColumnas) {
            return false;
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            if (palabra.charAt(i) != matriz[fila - i][columna + i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean diagonalDerechaIzquierdaInvertida(char[][] matriz, String palabra, int fila,
            int columna) {

        int longitud = palabra.length();

        if (fila - longitud < 0 || columna - longitud < 0) {
            return false;
        }

        for (int i = 0; i < longitud; i++) {
            pasosEnSopa++;
            if (palabra.charAt(i) != matriz[fila - i][columna - i]) {
                return false;
            }
        }
        return true;
    }

    // Esta función busca una palabra en todas las direcciones en una matriz de
    // letras (sopa de letras).
    public static boolean buscarPalabraEnSopa(char[][] matriz, String palabra) {
        int numFilas = matriz.length;
        int numColumnas = matriz[0].length;
        // Recorremos todas las celdas de la matriz.
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                // Comprobamos si la palabra se encuentra en todas las direcciones posibles.
                if (horizontalDirecto(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i, j + palabra.length() - 1, "Horizontal directa");
                    return true;
                }
                if (horizontalInvertida(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i, j - palabra.length() + 1, "Horizontal invertida");
                    return true;
                }
                if (verticalDirecto(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i + palabra.length() - 1, j, "Vertical directa");
                    return true;
                }
                if (verticalInvertida(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i - palabra.length() + 1, j, "Vertical Invertida");
                    return true;
                }
                if (diagonalIzquierdaDerechaDirecto(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i + palabra.length() - 1, j + palabra.length() - 1,
                            "Diagonal directa de izquierda a derecha");
                    return true;
                }
                if (diagonalDerechaIzquierdaDirecto(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i + palabra.length() - 1, j - palabra.length() + 1,
                            "Diagonal directa de derecha a izquierda");
                    return true;
                }
                if (diagonalIzquierdaDerechaInvertida(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i - palabra.length() + 1, j + palabra.length() - 1,
                            "Diagonal invertida de izquierda a derecha");
                    return true;
                }
                if (diagonalDerechaIzquierdaInvertida(matriz, palabra, i, j)) {
                    imprimirResultado(palabra, i, j, i - palabra.length() + 1, j - palabra.length() + 1,
                            "Diagonal invertida de derecha a izquierda");
                    return true;
                }
            }
        }
        System.out.println("-> " + palabra + ", NO se encontró en la sopa de letras.");
        return false;
    }

    public static void imprimirResultado(String palabra, int filaInicio, int columnaInicio, int filaFin, int columnaFin,
            String tipo) {
        System.out.println(
                "-> " + palabra + ",  la encontro en:   (" + (filaInicio + 1) + "," + (columnaInicio + 1) + ") hasta ("
                        + (filaFin + 1) + "," + (columnaFin + 1) + ") como " + tipo + ".");
    }

    public static void main(String[] args) {
        // Matriz de caracteres
        char[][] Sopa = {
                { 'm', 'a', 'e', 's', 't', 'u', 'd', 'i', 'a', 'y' },
                { 'a', 'j', 'z', 'e', 'o', 'h', 'c', 'u', 'u', 'a' },
                { 'r', 'e', 'o', 'p', 'u', 'o', 'c', 'n', 'c', 'y' },
                { 'i', 'l', 'z', 's', 'o', 'q', 'a', 'e', 'k', 'P' },
                { 'a', 'a', 'z', 's', 'i', 'r', 'b', 'l', 'l', 'e' },
                { 'o', 'o', 'h', 'c', 'u', 'm', 'p', 'p', 'e', 'p' }
        };

        Scanner scanner = new Scanner(System.in);
        // Mostramos la sopa de letras en la consola.
        System.out.println("Sopa de letras:");
        System.out.println("");
        int filas;
        int columnas;
        // Recorremos la matriz y mostramos cada carácter en su posición
        // correspondiente.
        for (filas = 0; filas < Sopa.length; filas++) {
            for (columnas = 0; columnas < Sopa[filas].length; columnas++) {
                System.out.print(Sopa[filas][columnas] + " ");
            }
            System.out.println();
        }
        System.out.println("");

        System.out.println("Ingrese la frase a buscar: ");
        String frase = scanner.nextLine();

        // Dividimos la frase en palabras individuales.
        String[] palabras = frase.split(" "); // Divide la frase
        boolean PalEncontrada = false;

        // Buscar cada palabra en la matriz
        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            // Llamamos a la función buscarPalabraEnSopa para buscar la palabra en la sopa
            // de letras.
            if (buscarPalabraEnSopa(Sopa, palabra)) {
                PalEncontrada = true;
            }
        }
        // Si ninguna palabra se encontró en la sopa de letras, mostramos un mensaje.
        if (!PalEncontrada) {
            System.out.println("Ninguna palabra ingresada está en la sopa de letras.");

        }
        // Mostramos el número total de pasos realizados en la búsqueda.
        System.out.println("Pasos totales realizados:" + pasosEnSopa);
        scanner.close();
    }
}