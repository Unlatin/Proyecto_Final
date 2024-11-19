package proyecto1;

import java.util.*;

public class SistemaLogistico {
    private List<Vehiculo> vehiculos;
    private PriorityQueue<Solicitud> solicitudes;  // Cola de prioridad
    private TreeSet<String> clientesNombres;  // Árbol para buscar clientes por nombre

    public SistemaLogistico() {
        vehiculos = new ArrayList<>();
        solicitudes = new PriorityQueue<>(new Comparator<Solicitud>() {
            @Override
            public int compare(Solicitud s1, Solicitud s2) {
                // Primero por nombre alfabético
                int nombreComparison = s1.getCliente().getNombre().compareTo(s2.getCliente().getNombre());
                if (nombreComparison != 0) {
                    return nombreComparison;
                }
                // Si los nombres son iguales, se compara por peso de la mercancía (descendente)
                return Double.compare(s2.getPeso(), s1.getPeso());
            }
        });
        clientesNombres = new TreeSet<>();
        inicializarVehiculos();
    }

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
        clientesNombres.add(solicitud.getCliente().getNombre());  // Añadir el nombre del cliente al TreeSet
    }

    // Procesar y asignar solicitudes a los vehículos
    public void procesarSolicitudes() {
        while (!solicitudes.isEmpty()) {
            Solicitud solicitud = solicitudes.poll();
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

    // Método para buscar un cliente por nombre
    public boolean buscarClientePorNombre(String nombreCliente) {
        return clientesNombres.contains(nombreCliente);
    }
}
