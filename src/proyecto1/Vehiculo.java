
package proyecto1;

/**
 *
 * @author EQUIPO
 */


import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    private String tipo;
    private double capacidadCarga; // En toneladas
    private List<Solicitud> solicitudes;

    public Vehiculo(String tipo, double capacidadCarga) {
        this.tipo = tipo;
        this.capacidadCarga = capacidadCarga;
        this.solicitudes = new ArrayList<>();
    }

    // Obtener el tipo de vehículo
    public String getTipo() {
        return tipo;
    }

    // Obtener las solicitudes asignadas al vehículo
    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    // Verificar si el vehículo puede cargar una solicitud
    public boolean puedeCargar(Solicitud solicitud) {
        double cargaActual = solicitudes.stream().mapToDouble(Solicitud::getPeso).sum();
        double nuevaCarga = cargaActual + solicitud.getPeso();
        return nuevaCarga <= capacidadCarga * 1000; // Convertimos toneladas a kilogramos
    }

    // Agregar una solicitud al vehículo
    public void agregarSolicitud(Solicitud solicitud) {
        if (puedeCargar(solicitud)) {
            solicitudes.add(solicitud);
        } else {
            throw new IllegalArgumentException("La carga excede la capacidad del vehículo.");
        }
    }

    // Obtener la capacidad de carga del vehículo
    public double getCapacidadCarga() {
        return capacidadCarga;
    }
}

