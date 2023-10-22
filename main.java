import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Muestra las opciones de regresión disponibles
        System.out.println("Seleccione el tipo de regresión:");
        System.out.println("1. Regresión lineal");
        System.out.println("2. Regresión lineal cuadrática");
        System.out.println("3. Regresión lineal múltiple");
        System.out.println("4. Regresión polinomial");

        // Lee la opción seleccionada por el usuario
        int opcion = scanner.nextInt();

        // Ejecuta el programa correspondiente según la opción seleccionada
        switch (opcion) {
            case 1:
                regresionlineal.main(args);
                break;
            case 2:
                RegresionLinealCuadratica.main(args);
                break;
            case 3:
                RegresionLinealMultiple.main(args);
                break;
            case 4:
                RegresionPolinomial.main(args);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

}
