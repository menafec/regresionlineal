import java.util.Arrays;

public class RegresionPolinomial {

    // Función para ajustar una regresión polinomial a un conjunto de datos
    public static double[] ajustarRegresionPolinomial(double[][] datos, int grado) {
        int n = datos.length;

        // Construye la matriz de coeficientes
        double[][] matrizA = new double[grado + 1][grado + 1];
        double[] b = new double[grado + 1];
        for (int i = 0; i <= grado; i++) {
            for (int j = 0; j <= grado; j++) {
                double sum = 0.0;
                for (int k = 0; k < n; k++) {
                    sum += Math.pow(datos[k][0], i + j);
                }
                matrizA[i][j] = sum;
            }
            double sum = 0.0;
            for (int k = 0; k < n; k++) {
                sum += datos[k][1] * Math.pow(datos[k][0], i);
            }
            b[i] = sum;
        }

        // Resuelve el sistema de ecuaciones
        double[] coeficientes = resolverSistema(matrizA, b);

        return coeficientes;
    }

    // Función para resolver un sistema de ecuaciones lineales
    public static double[] resolverSistema(double[][] matrizA, double[] b) {
        int n = matrizA.length;

        // Eliminación Gaussiana
        for (int i = 0; i < n; i++) {
            // Encuentra el elemento máximo en la columna i
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matrizA[j][i]) > Math.abs(matrizA[maxIndex][i])) {
                    maxIndex = j;
                }
            }

            // Intercambia la fila actual con la fila con el elemento máximo
            double[] tempA = matrizA[i];
            matrizA[i] = matrizA[maxIndex];
            matrizA[maxIndex] = tempA;

            double tempB = b[i];
            b[i] = b[maxIndex];
            b[maxIndex] = tempB;

            // Eliminación Gaussiana
            for (int j = i + 1; j < n; j++) {
                double factor = matrizA[j][i] / matrizA[i][i];
                b[j] -= factor * b[i];
                for (int k = i; k < n; k++) {
                    matrizA[j][k] -= factor * matrizA[i][k];
                }
            }
        }

        // Sustitución hacia atrás
        double[] solucion = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += matrizA[i][j] * solucion[j];
            }
            solucion[i] = (b[i] - sum) / matrizA[i][i];
        }

        return solucion;
    }

    // Función para evaluar el polinomio en un valor dado
    public static double evaluarPolinomio(double[] coeficientes, double x) {
        double resultado = 0.0;
        for (int i = 0; i < coeficientes.length; i++) {
            resultado += coeficientes[i] * Math.pow(x, i);
        }
        return resultado;
    }

    // Método main para probar la clase
    public static void main(String[] args) {
        // Ejemplo de uso
        double[][] datos = {{108, 95}, {115, 96}, {106, 95}, {97, 97}, {95, 93}, {91, 94}, {97, 95}, {83, 93}, {83, 92}, {78, 86}, {54, 83}, {67, 80}, {56, 65}, {53, 69}, {61, 77}, {115, 96}, {81, 87}, {78, 89}, {30, 60}, {45, 63}, {99, 95}, {32, 61}, {25, 55}, {28, 56}, {90, 94}, {89, 93}};
        int grado = 2;

        double[] coeficientes = ajustarRegresionPolinomial(datos, grado);
        System.out.println("Coeficientes: " + Arrays.toString(coeficientes));

        double x = 100.0;
        double y = evaluarPolinomio(coeficientes, x);
        System.out.println("Valor de y para x = " + x + ": " + y);
    }
}

