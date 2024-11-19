package proyecto1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaLogistico sistema = new SistemaLogistico();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Sistema Logistico ");
        System.out.println("Por favor, ingrese las solicitudes de transporte.");
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nDatos del Cliente:");
            System.out.print("Nombre del cliente: ");
            String nombreCliente = scanner.nextLine();
            System.out.print("Ciudad de destino: ");
            String ciudadDestino = scanner.nextLine();

            System.out.println("\nDatos de la Mercancía:");
            System.out.print("Peso (en kilogramos): ");
            double peso = Double.parseDouble(scanner.nextLine());
            System.out.print("Volumen (en metros cúbicos): ");
            double volumen = Double.parseDouble(scanner.nextLine());

            Cliente cliente = new Cliente(nombreCliente, ciudadDestino);
            Solicitud solicitud = new Solicitud(cliente, peso, volumen, ciudadDestino);
            sistema.agregarSolicitud(solicitud);

            System.out.print("\nDesea agregar otra solicitud? (s/n): ");
            String respuesta = scanner.nextLine();
            continuar = respuesta.equalsIgnoreCase("s");
        }

        System.out.println("\nProcesando solicitudes...");
        sistema.procesarSolicitudes();

        System.out.println("\nEstado Final de los Vehículos:");
        sistema.mostrarEstadoVehiculos();

        // Ejemplo de búsqueda de cliente
        System.out.print("\n¿Desea buscar un cliente por nombre? (s/n): ");
        String buscarRespuesta = scanner.nextLine();
        if (buscarRespuesta.equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nombre del cliente a buscar: ");
            String nombreBuscar = scanner.nextLine();
            if (sistema.buscarClientePorNombre(nombreBuscar)) {
                System.out.println("El cliente " + nombreBuscar + " existe en el sistema.");
            } else {
                System.out.println("El cliente " + nombreBuscar + " no se encuentra en el sistema.");
            }
        }

        System.out.println("\nGracias por usar el Sistema Logistico.");
        scanner.close();
    }
}
