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
public class editorial implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private boolean alta;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public editorial() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return "editorial{" + "id=" + id + ", nombre=" + nombre + ", alta=" + alta + '}';
    }
    
}
