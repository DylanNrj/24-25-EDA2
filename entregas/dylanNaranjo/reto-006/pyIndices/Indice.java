package entregas.dylanNaranjo.reto006.pyIndices;

import java.util.Arrays;

public class Indice {
    private String[] valores;
    private int[][] posiciones;
    private int[] contadores;
    private int cantidadValores;

    public Indice(int capacidadMaxima) {
        valores = new String[capacidadMaxima];
        posiciones = new int[capacidadMaxima][capacidadMaxima];
        contadores = new int[capacidadMaxima];
        cantidadValores = 0;
    }

    public void agregar(String valor, int posicion) {
        // Buscar la posición donde insertar el valor usando búsqueda binaria
        int indiceValor = Arrays.binarySearch(valores, 0, cantidadValores, valor);

        if (indiceValor < 0) { // Si no existe, se inserta en orden
            indiceValor = -(indiceValor + 1); // Convertir índice negativo a posición de inserción

            // Desplazar valores, posiciones y contadores para hacer espacio
            System.arraycopy(valores, indiceValor, valores, indiceValor + 1, cantidadValores - indiceValor);
            System.arraycopy(posiciones, indiceValor, posiciones, indiceValor + 1, cantidadValores - indiceValor);
            System.arraycopy(contadores, indiceValor, contadores, indiceValor + 1, cantidadValores - indiceValor);

            // Insertar el nuevo valor
            valores[indiceValor] = valor;
            contadores[indiceValor] = 0; // Inicializar contador para el nuevo valor
            cantidadValores++;
        }

        // Agregar la posición al valor correspondiente
        posiciones[indiceValor][contadores[indiceValor]] = posicion;
        contadores[indiceValor]++;
    }

    public int[] buscar(String valor) {
        // Buscar el índice del valor usando búsqueda binaria
        int indiceValor = Arrays.binarySearch(valores, 0, cantidadValores, valor);

        if (indiceValor < 0) {
            return new int[0]; // Si no se encuentra, devolver un arreglo vacío
        }

        // Devolver las posiciones asociadas al valor
        return Arrays.copyOf(posiciones[indiceValor], contadores[indiceValor]);
    }

    public boolean contiene(String valor) {
        // Verificar si el valor existe usando búsqueda binaria
        return Arrays.binarySearch(valores, 0, cantidadValores, valor) >= 0;
    }

    public String[] obtenerTodos() {
        // Devolver una copia de los valores ordenados
        return Arrays.copyOf(valores, cantidadValores);
    }
}