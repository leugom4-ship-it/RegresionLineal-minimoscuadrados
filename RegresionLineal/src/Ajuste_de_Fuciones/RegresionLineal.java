package Ajuste_de_Fuciones;

public class RegresionLineal {

    public static double[] calcularMinimosCuadrados(double[] x, double[] y) {

        if (x == null || y == null || x.length != y.length || x.length < 2) {
            System.out.println("Error en los datos");
            return new double[]{0, 0, 0, 0};
        }

        int n = x.length;

        double sumX = 0;
        double sumY = 0;
        double sumX2 = 0;
        double sumY2 = 0;
        double sumXY = 0;

        // Calcular sumatorias
        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumX2 += x[i] * x[i];
            sumY2 += y[i] * y[i];
            sumXY += x[i] * y[i];
        }

        // Calcular pendiente y ordenada
        double numerador = n * sumXY - sumX * sumY;
        double denX = n * sumX2 - sumX * sumX;
        double denY = n * sumY2 - sumY * sumY;

        if (denX == 0) {
            System.out.println("Error: X no varia");
            return new double[]{0, 0, 0, 0};
        }

        double beta1 = numerador / denX;
        double beta0 = (sumY - beta1 * sumX) / n;

        // Calcular Pearson
        double pearsonR = 0;
        double r2 = 0;

        if (denY != 0) {
            pearsonR = numerador / Math.sqrt(denX * denY);
            r2 = pearsonR * pearsonR;
        }

        // Mostrar resultados
        System.out.println("RESULTADOS DE REGRESION LINEAL");

        System.out.printf("Pendiente: %.4f%n", beta1);
        System.out.printf("Ordenada: %.4f%n", beta0);
        System.out.printf("Modelo: Y = %.4f + %.4fX%n", beta0, beta1);
        System.out.printf("Pearson: %.4f%n", pearsonR);
        System.out.printf("Determinacion r2: %.4f%n", r2);

        return new double[]{beta0, beta1, pearsonR, r2};
    }

    public static double predecir(double beta0, double beta1, double x) {
        return beta0 + beta1 * x;
    }

    public static void main(String[] args) {

        double[] x = {1, 2, 3, 4, 5};
        double[] y = {2, 3, 5, 4, 6};

        double[] resultado = calcularMinimosCuadrados(x, y);

        double prediccion = predecir(resultado[0], resultado[1], 6);

        System.out.printf("Prediccion para X=6: %.4f%n", prediccion);
    }
}