import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese cantidad de Personas del listado: ");
        int n = sc.nextInt();
        sc.nextLine();

        Persona[] personas = new Persona[n];

        // INGRESO DE PERSONAS
        for (int i = 0; i < n; i++) {

            System.out.println("Ingrese Persona:");

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            int edad;

            do {
                System.out.print("Edad: ");
                edad = sc.nextInt();

                if (edad < 0) {
                    System.out.println("La edad no puede ser negativa.");
                }

            } while (edad < 0);

            sc.nextLine();

            personas[i] = new Persona(nombre, edad);
        }

        // ORDENAR POR EDAD (BURBUJA)
        for (int i = 0; i < personas.length - 1; i++) {

            for (int j = 0; j < personas.length - 1 - i; j++) {

                if (personas[j].getEdad() > personas[j + 1].getEdad()) {

                    Persona aux = personas[j];
                    personas[j] = personas[j + 1];
                    personas[j + 1] = aux;
                }
            }
        }

        // BUSCAR EDAD
        System.out.print("Valor de edad de la persona que se desea buscar: ");
        int edadBuscar = sc.nextInt();

        busquedaBinaria(personas, edadBuscar);

        sc.close();
    }

    public static void busquedaBinaria(Persona[] personas, int edadBuscar) {

        int bajo = 0;
        int alto = personas.length - 1;

        boolean encontrado = false;

        while (bajo <= alto) {

            // MOSTRAR SUBARREGLO ACTUAL
            for (int i = bajo; i <= alto; i++) {
                System.out.print(personas[i].getEdad() + " | ");
            }

            System.out.println();

            int centro = (bajo + alto) / 2;

            int valorCentro = personas[centro].getEdad();

            System.out.print(
                    "bajo=" + bajo +
                    " alto=" + alto +
                    " centro=" + centro +
                    " valorCentro=" + valorCentro
            );

            // ENCONTRADO
            if (valorCentro == edadBuscar) {

                // BUSCAR LA PRIMERA PERSONA CON ESA EDAD
                int primeraPosicion = centro;

                while (primeraPosicion > 0 &&
                        personas[primeraPosicion - 1].getEdad() == edadBuscar) {

                    primeraPosicion--;
                }

                System.out.println(" --> ENCONTRADO");

                System.out.println(
                        "La persona con la edad " +
                        edadBuscar +
                        " es " +
                        personas[primeraPosicion].getNombre()
                );

                encontrado = true;
                break;
            }

            // DERECHA
            else if (valorCentro < edadBuscar) {

                System.out.println(" --> DERECHA");

                bajo = centro + 1;
            }

            // IZQUIERDA
            else {

                System.out.println(" --> IZQUIERDA");

                alto = centro - 1;
            }
        }

        if (!encontrado) {
            System.out.println("Elemento no encontrado.");
        }
    }
}