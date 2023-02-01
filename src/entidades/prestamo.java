package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author NeuenMartinez
 */

@Entity
public class prestamo implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private libro libro;
    private cliente cliente;
}
