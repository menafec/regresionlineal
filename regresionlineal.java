public class regresionlineal {

    public static void main(String[] args) {
        // Datos de ejemplo: X (variables independientes) y Y (variables dependientes)
        double[] X = {23, 26, 30, 34, 43 , 48, 52, 57, 58};
        double[] Y = {651, 762, 856, 1,063, 1,190, 1,298, 1,421, 1,440, 1,518};

        // Calcula la regresión lineal
        double[] coeficientes = calcularRegresionLineal(X, Y);

        // Imprime los coeficientes
        System.out.println("Coeficiente de la pendiente (a): " + coeficientes[0]);
        System.out.println("Intercepto (b): " + coeficientes[1]);

        // Valor X establecido dentro del código
        double valorX = 6;

        // Predicción de un valor futuro
        double valorY = predecirValor(coeficientes, valorX);
        System.out.println("Predicción para X=" + valorX + ": " + valorY);
    }

    // Función para calcular los coeficientes de regresión lineal
    public static double[] calcularRegresionLineal(double[] X, double[] Y) {
        int n = X.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += X[i];
            sumY += Y[i];
            sumXY += X[i] * Y[i];
            sumX2 += X[i] * X[i];
        }

        double pendiente = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercepto = (sumY - pendiente * sumX) / n;

        return new double[]{pendiente, intercepto};
    }

    // Función para predecir un valor futuro
    public static double predecirValor(double[] coeficientes, double valorX) {
        double pendiente = coeficientes[0];
        double intercepto = coeficientes[1];
        return pendiente * valorX + intercepto;
    }
}
