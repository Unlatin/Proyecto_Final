
package proyecto1;

/**
 *
 * @author EQUIPO
 */
public class Cliente {
    private final String nombre;
    private final String ciudad;

    public Cliente(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }
}