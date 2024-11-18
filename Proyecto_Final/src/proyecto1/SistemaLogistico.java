
package proyecto1;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SistemaLogistico {
    private List<Vehiculo> vehiculos;
    private List<Solicitud> solicitudes;

    public SistemaLogistico() {
        vehiculos = new ArrayList<>();
        solicitudes = new ArrayList<>();
        inicializarVehiculos();
    }

    // Inicialización de la flota de vehículos
    private void inicializarVehiculos() {
        // 50 vehículos de 18 toneladas
        for (int i = 0; i < 50; i++) {
            vehiculos.add(new Vehiculo("Camión 18 toneladas", 18.0));
        }
        // 50 vehículos de 7 toneladas
        for (int i = 0; i < 50; i++) {
            vehiculos.add(new Vehiculo("Camión 7 toneladas", 7.0));
        }
    }

    // Agregar solicitudes al sistema
    public void agregarSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }

    // Procesar y asignar solicitudes a los vehículos
    public void procesarSolicitudes() {
        // Ordenar las solicitudes según prioridad
        solicitudes.sort(Comparator.comparingDouble(Solicitud::getPrioridad).reversed());

        for (Solicitud solicitud : solicitudes) {
            for (Vehiculo vehiculo : vehiculos) {
                if (vehiculo.puedeCargar(solicitud)) {
                    vehiculo.agregarSolicitud(solicitud);
                    break;
                }
            }
        }
    }

    // Mostrar el estado actual de los vehículos
    public void mostrarEstadoVehiculos() {
        for (Vehiculo vehiculo : vehiculos) {
            double cargaActual = vehiculo.getSolicitudes().stream()
                                        .mapToDouble(Solicitud::getPeso)
                                        .sum();
            if (!vehiculo.getSolicitudes().isEmpty()) {
                System.out.println("Vehículo tipo: " + vehiculo.getTipo() +
                        " | Capacidad: " + vehiculo.getCapacidadCarga() + " toneladas" +
                        " | Carga actual: " + (cargaActual / 1000) + " toneladas");
                System.out.println("  Solicitudes asignadas:");
                vehiculo.getSolicitudes().forEach(solicitud -> 
                    System.out.println("    - Cliente: " + solicitud.getCliente().getNombre() +
                                       ", Ciudad destino: " + solicitud.getCiudadDestino() +
                                       ", Peso: " + solicitud.getPeso() + " kg, Volumen: " + solicitud.getVolumen() + " m³"));
            }
        }
    }
}
