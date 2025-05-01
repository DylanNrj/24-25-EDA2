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
        int indiceValor = Arrays.binarySearch(valores, 0, cantidadValores, valor);

        if (indiceValor < 0) {
            indiceValor = -(indiceValor + 1);

            System.arraycopy(valores, indiceValor, valores, indiceValor + 1, cantidadValores - indiceValor);
            System.arraycopy(posiciones, indiceValor, posiciones, indiceValor + 1, cantidadValores - indiceValor);
            System.arraycopy(contadores, indiceValor, contadores, indiceValor + 1, cantidadValores - indiceValor);
            valores[indiceValor] = valor;
            contadores[indiceValor] = 0;
            cantidadValores++;
        }

        posiciones[indiceValor][contadores[indiceValor]] = posicion;
        contadores[indiceValor]++;
    }

    public int[] buscar(String valor) {
        int indiceValor = Arrays.binarySearch(valores, 0, cantidadValores, valor);

        if (indiceValor < 0) {
            return new int[0];
        }

        return Arrays.copyOf(posiciones[indiceValor], contadores[indiceValor]);
    }

    public boolean contiene(String valor) {
        return Arrays.binarySearch(valores, 0, cantidadValores, valor) >= 0;
    }

    public String[] obtenerTodos() {
        return Arrays.copyOf(valores, cantidadValores);
    }
}