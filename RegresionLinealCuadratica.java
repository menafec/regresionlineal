public class RegresionLinealCuadratica {

    public static void main(String[] args) {
        // Datos de ejemplo: Valores de X y resultados Y
        double[] X = {-3, -2, -1, 0, 1, 2, 3};
        double[] Y = {7.5, 3, 0.5, 1, 3, 6, 14};

        // Calcula la regresión lineal cuadrática
        double[] coeficientes = calcularRegresionLinealCuadratica(X, Y);

        // Imprime los coeficientes
        System.out.println("Coeficiente a: " + coeficientes[0]);
        System.out.println("Coeficiente b: " + coeficientes[1]);
        System.out.println("Coeficiente c: " + coeficientes[2]);

        // Valor X de prueba
        double valorX = 6;

        // Predicción de un valor Y para el valor X de prueba
        double valorY = predecirValor(coeficientes, valorX);
        System.out.println("Predicción para X=" + valorX + ": " + valorY);
    }

    // Función para calcular los coeficientes de regresión lineal cuadrática
    public static double[] calcularRegresionLinealCuadratica(double[] X, double[] Y) {
        int n = X.length;

        double sumX = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumY = 0, sumXY = 0, sumX2Y = 0;

        for (int i = 0; i < n; i++) {
            double x = X[i];
            double y = Y[i];

            sumX += x;
            sumX2 += x * x;
            sumX3 += x * x * x;
            sumX4 += x * x * x * x;
            sumY += y;
            sumXY += x * y;
            sumX2Y += x * x * y;
        }

        // Resuelve el sistema de ecuaciones para obtener los coeficientes
        double[][] sistemaEcuaciones = {
            {n, sumX, sumX2, sumY},
            {sumX, sumX2, sumX3, sumXY},
            {sumX2, sumX3, sumX4, sumX2Y}
        };

        double[] coeficientes = resolverSistemaEcuaciones(sistemaEcuaciones);

        return coeficientes;
    }

    // Función para resolver un sistema de ecuaciones lineales
    public static double[] resolverSistemaEcuaciones(double[][] sistemaEcuaciones) {
        int n = sistemaEcuaciones.length;

        for (int i = 0; i < n; i++) {
            // Pivoteo parcial: buscar el máximo elemento en la columna actual
            double max = Math.abs(sistemaEcuaciones[i][i]);
            int maxRow = i;

            for (int k = i + 1; k < n; k++) {
                if (Math.abs(sistemaEcuaciones[k][i]) > max) {
                    max = Math.abs(sistemaEcuaciones[k][i]);
                    maxRow = k;
                }
            }

            // Intercambiar filas
            double[] temp = sistemaEcuaciones[i];
            sistemaEcuaciones[i] = sistemaEcuaciones[maxRow];
            sistemaEcuaciones[maxRow] = temp;

            // Eliminación gaussiana
            for (int k = i + 1; k < n; k++) {
                double factor = -sistemaEcuaciones[k][i] / sistemaEcuaciones[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        sistemaEcuaciones[k][j] = 0;
                    } else {
                        sistemaEcuaciones[k][j] += factor * sistemaEcuaciones[i][j];
                    }
                }
            }
        }

        // Sustitución hacia atrás
        double[] coeficientes = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            coeficientes[i] = sistemaEcuaciones[i][n] / sistemaEcuaciones[i][i];
            for (int k = i - 1; k >= 0; k--) {
                sistemaEcuaciones[k][n] -= sistemaEcuaciones[k][i] * coeficientes[i];
            }
        }

        return coeficientes;
    }

    // Función para predecir un valor Y para un valor X de prueba
    public static double predecirValor(double[] coeficientes, double valorX) {
        double a = coeficientes[0];
        double b = coeficientes[1];
        double c = coeficientes[2];

        double valorY = a + b * valorX + c * valorX * valorX;

        return valorY;
    }
}
