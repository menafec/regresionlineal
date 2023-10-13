public class RegresionLinealMultiple {

    public static void main(String[] args) {
        // Datos de ejemplo: Matriz de variables independientes (X) y resultados (Y)
        double[][] X = {
            {41.9, 29.1},
            {43.4, 29.3},
            {43.9, 29.5},
            {44.5, 29.7},
            {47.3, 29.9},
            {47.5, 30.3},
            {47.9, 30.5},
            {50.2, 30.7},
            {52.8, 30.8},
            {53.2, 30.9},
            {56.7, 31.5},
            {57.0, 31.7},
            {63.5, 31.9},
            {65.3, 32.0},
            {71.1, 32.1},
            {77.0, 32.5},
            {77.8, 32.9}
        };

        double[] Y = {251.3, 251.3, 248.3, 267.5, 273.0, 276.5, 270.3, 274.9, 285.0, 290.0, 297.0, 302.5, 304.5, 309.3, 321.7, 330.7, 349.0};

        // Calcula la regresión lineal múltiple
        double[] coeficientes = calcularRegresionLinealMultiple(X, Y);

        // Imprime los coeficientes
        System.out.println("Coeficientes:");
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.println("Coeficiente " + i + ": " + coeficientes[i]);
        }

        // Valores X de prueba
        double[] valoresX = {80.0, 35.0};

        // Predicción de valores Y para los valores X de prueba
        double valorY = predecirValor(coeficientes, valoresX);
        System.out.println("Predicción para X=" + java.util.Arrays.toString(valoresX) + ": " + valorY);
    }

    // Función para calcular los coeficientes de regresión lineal múltiple sin bibliotecas externas
    public static double[] calcularRegresionLinealMultiple(double[][] X, double[] Y) {
        int n = X.length;
        int m = X[0].length;

        // Construye la matriz X transpuesta
        double[][] XTranspuesta = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                XTranspuesta[j][i] = X[i][j];
            }
        }

        // Calcula la matriz de productos X transpuesta por X
        double[][] XTranspuestaPorX = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += XTranspuesta[i][k] * X[k][j];
                }
                XTranspuestaPorX[i][j] = sum;
            }
        }

        // Calcula la matriz de productos X transpuesta por Y
        double[] XTranspuestaPorY = new double[m];
        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += XTranspuesta[i][j] * Y[j];
            }
            XTranspuestaPorY[i] = sum;
        }

        // Resuelve el sistema de ecuaciones lineales
        double[] coeficientes = new double[m];
        for (int i = 0; i < m; i++) {
            coeficientes[i] = XTranspuestaPorY[i];
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = i + 1; j < m; j++) {
                coeficientes[i] -= XTranspuestaPorX[i][j] * coeficientes[j];
            }
            coeficientes[i] /= XTranspuestaPorX[i][i];
        }

        return coeficientes;
    }

    // Función para predecir el valor de Y para un conjunto de valores X
    public static double predecirValor(double[] coeficientes, double[] valoresX) {
        double sum = 0;
        for (int i = 0; i < coeficientes.length; i++) {
            sum += coeficientes[i] * valoresX[i];
        }
        return sum;
    }
}