package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author NeuenMartinez
 */
@Entity
public class cliente implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Long DNI;
    private String nombre;
    private String apellido;
    private String telefono;

    public cliente() {
    }

    public cliente(Integer id, Long DNI, String nombre, String apellido, String telefono) {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDNI() {
        return DNI;
    }

    public void setDNI(Long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "cliente{" + "id=" + id + ", DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + '}';
    }
    
    
}
