import java.util.Scanner;
public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione el tipo de regresion:");
        System.out.println("1. Regresion lineal");
        System.out.println("2. Regresion lineal cuadratica");
        System.out.println("3. Regresion lineal multiple");

        int opcion = scanner.nextInt();

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
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

}