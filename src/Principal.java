import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        boolean continuar = true;
        List<Conversor> conversiones = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (continuar){
            System.out.println("***********************************************");
            System.out.println("¡BIENVENIDOS AL CONVERSOR DE MONEDAS!");
            System.out.println("***********************************************");

            System.out.println("Seleccione una opción");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Salir");

            try {
                int opcion = lectura.nextInt();
                lectura.nextLine();

                String menu = """
                ***ALGUNAS MONEDAS DISPONIBLES***
                ARS - Peso argentino
                BOB - Boliviano boliviano
                BRL - Real brasileño
                CLP - Peso chileno
                COP - Peso colombiano
                USD - Dólar estadounidense
                PEN - Sol peruano
                EUR - Euro unión europea
                MXN - Peso mexicano
                UYU - Peso uruguayo
                JPY - Yen japones
                etc, etc
                """;
                switch (opcion){
                    case 1:
                        System.out.println(menu);
                        System.out.println("Ingrese la moneda origen: ");
                        String monedaBase = lectura.nextLine();

                        System.out.println("Ingrese la moneda a convertir: ");
                        String monedaTarget = lectura.nextLine();

                        System.out.println("Ingrese el monto que desea convertir: ");
                        double monto = lectura.nextDouble();

                        // Llamar al metodo convertirMoneda que ahora devuelve un objeto conversor
                        Conversor conversor = consulta.convertirMoneda(monedaBase, monedaTarget, monto);
                        System.out.println(conversor);//Imprimir conversor
                        conversiones.add(conversor); // Agregar el objeto Conversor a la lista

                        System.out.println("Tasa de conversion actual: \n1 "+conversor.getMonedaBase()+ " = "+conversor.getTasaDeConversion()+" "+conversor.getMonedaTarget());
                        System.out.println("Resultado: \n"+monto+" "+conversor.getMonedaBase() +" = " + conversor.getMontoConvertido() + " " + conversor.getMonedaTarget());
                        break;

                    case 2:
                        continuar = false;
                        System.out.println("¡Gracias por utilizar el conversor de monedas!");
                        break;

                    default:
                        System.out.println("Opcion no válida. Por favor selecciona una opcion válida.");
                        break;

                }
            }catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                lectura.nextLine(); // Limpiar el buffer en caso de error
                continue; // Volver al inicio del bucle
            }
        }
        lectura.close();

        FileWriter escritura = new FileWriter("conversiones.json");
        escritura.write(gson.toJson(conversiones));
        escritura.close();
        System.out.println("Finalizó la ejecución del programa!");
    }
}
