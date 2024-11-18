
package proyecto1;

/**
 *
 * @author EQUIPO
 */
public class Solicitud {
    private final Cliente cliente;
    private final double peso;
    private final double volumen;
    private final String ciudadDestino;
    private int prioridad;

    public Solicitud(Cliente cliente, double peso, double volumen, String ciudadDestino) {
        this.cliente = cliente;
        this.peso = peso;
        this.volumen = volumen;
        this.ciudadDestino = ciudadDestino;
        calcularPrioridad();
    }

    private void calcularPrioridad() {
        prioridad = (int) (peso / volumen);
    }

    public int getPrioridad() {
        return prioridad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPeso() {
        return peso;
    }

    public double getVolumen() {
        return volumen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "cliente=" + cliente.getNombre() +
                ", peso=" + peso +
                ", volumen=" + volumen +
                ", ciudadDestino='" + ciudadDestino + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }
}