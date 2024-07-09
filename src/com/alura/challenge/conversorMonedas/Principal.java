package com.alura.challenge.conversorMonedas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) throws Exception {
        Map<String, String> countryToCurrencyMap = new HashMap<>();
        countryToCurrencyMap.put("Mexico", "MXN");
        countryToCurrencyMap.put("USA", "USD");
        countryToCurrencyMap.put("Argentina", "ARS");
        countryToCurrencyMap.put("Brazil", "BRL");
        countryToCurrencyMap.put("Colombia", "COP");

        ConversorMoneda conversor = new ConversorMoneda(countryToCurrencyMap);
        //double resultado = conversor.buscadorMonedas("USA", "Colombia", 100);
        //System.out.println("El resultado de la conversión es: " + resultado);

        //----------------------------------------------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")){
            int opcion = 0;
            do {
                System.out.println("\n************************************");
                System.out.println("** Bienvenido al Conversor de Moneda **");
                System.out.println("** Escriba la opcion deseada: **");

                System.out.println("1. Dólar a Peso mexicano"); //Mexico
                System.out.println("2. Peso mexicano a Dólar");

                System.out.println("3. Dólar a Peso argentino"); //Argentina
                System.out.println("4. Peso argentino a Dólar");

                System.out.println("5. Dólar a Real brasileño"); //Brasil
                System.out.println("6. Real brasileño a Dólar");

                System.out.println("7. Dólar a Peso colombiano"); //Colombia
                System.out.println("8. Peso colombiano a Dólar");


                System.out.println("9. Salir");
                System.out.println("\n************************************");
                System.out.print("R:");

                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente


                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el valor que desea convertir de [USD] a [MXN]:");
                        double valor1 = scanner.nextDouble();
                        double resultado1 = conversor.buscadorMonedas("USA", "Mexico", valor1);
                        System.out.println("El valor de "+valor1+"[USD] corresponde al valor de =>>> " + resultado1+ "[MXN]");

                        System.out.println("\n-------------------------\n");

                        break;
                    case 2:
                        System.out.print("Ingrese el valor que desea convertir de [MXN] a [USD]:");
                        double valor11 = scanner.nextDouble();
                        double resultado11 = conversor.buscadorMonedas("Mexico", "USA", valor11);
                        System.out.println("El valor de "+valor11+"[MXN] corresponde al valor de =>>> " + resultado11+ "[USD]");
                        System.out.println("\n-------------------------\n");
                        break;
                    case 3:
                        System.out.print("Ingrese el valor que desea convertir de [USD] a [ARS]:");
                        double valor2 = scanner.nextDouble();
                        double resultado2 = conversor.buscadorMonedas("USA", "Argentina", valor2);
                        System.out.println("El valor de "+valor2+"[USD] corresponde al valor de =>>> " + resultado2+ "[ARS]");
                        System.out.println("\n-------------------------\n");
                        break;

                    case 4:
                        System.out.print("Ingrese el valor que desea convertir de [ARS] a [USD]: ");
                        double valor = scanner.nextDouble();
                        scanner.nextLine(); // Consumir el carácter de nueva línea pendiente en el buffer

                        try {
                            // Realizar la conversión
                            double resultado = conversor.buscadorMonedas("Argentina", "USA", valor);

                            // Mostrar el resultado
                            System.out.println("El valor de " + valor + "[ARS] corresponde al valor de =>>> " + resultado + "[USD]");
                        } catch (Exception e) {
                            System.out.println("Error al realizar la conversión: " + e.getMessage());
                        }
                        System.out.println("\n-------------------------\n");
                        break;
                    case 5:
                        System.out.print("Ingrese el valor que desea convertir de [USD] a [BRL]:");
                        double valor4 = scanner.nextDouble();
                        double resultado4 = conversor.buscadorMonedas("USA", "Brazil", valor4);
                        System.out.println("El valor de "+valor4+"[USD] corresponde al valor de =>>> " + resultado4+ "[BRL]");
                        System.out.println("\n-------------------------\n");
                        break;
                    case 6:
                        System.out.print("Ingrese el valor que desea convertir de [BRL] a [USD]:");
                        double valor44 = scanner.nextDouble();
                        double resultado44 = conversor.buscadorMonedas("Brazil", "USA", valor44);
                        System.out.println("El valor de "+valor44+"[Brazil] corresponde al valor de =>>> " + resultado44+ "[USA]");
                        System.out.println("\n-------------------------\n");
                        break;
                    case 7:
                        System.out.print("Ingrese el valor que desea convertir de [USD] a [COP]:");
                        double valor7 = scanner.nextDouble();
                        double resultado7 = conversor.buscadorMonedas("USA", "Colombia", valor7);
                        System.out.println("El valor de "+valor7+"[USA] corresponde al valor de =>>> " + resultado7+ "[Colombia]");
                        System.out.println("\n-------------------------\n");
                        break;
                    case 8:
                        System.out.print("Ingrese el valor que desea convertir de [COP] a [USA]:");
                        double valor8 = scanner.nextDouble();
                        double resultado8 = conversor.buscadorMonedas("Colombia", "USA", valor8);
                        System.out.println("El valor de "+valor8+"[COP] corresponde al valor de =>>> " + resultado8+ "[USA]");
                        System.out.println("\n-------------------------\n");
                        break;
                    case 9:
                        continuar = "n";
                        System.out.println("¡Hasta luego!");
                        System.out.println("\n-------------------------\n");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        System.out.println("\n-------------------------\n");


                }
            }while (opcion != 9) ;

        }
        scanner.close();


    }

}