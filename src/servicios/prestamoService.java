package servicios;

import entidades.cliente;
import entidades.libro;
import entidades.prestamo;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author NeuenMartinez
 */
public class prestamoService {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearPrestamo() {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                prestamo prestamo = new prestamo();

                try {
                    System.out.println("Ingresar el codigo ISBN del libro a retirar:");
                    libro libro = em.find(libro.class, leer.nextLong());
                    if (libro.getEjemplaresRestantes() == 0) {
                        throw new Exception("No se encuetran ejemplares disponibles...");
                    }
                    prestamo.setLibro(libro);
                } catch (Exception e) {
                    System.out.println("El codigo del libro ingresado no existe en el sistema");
                    return;
                }
                try {
                    System.out.println("Ingresar el ID del cliente");
                    cliente cliente = em.find(cliente.class, leer.nextInt());
                    prestamo.setCliente(cliente);
                } catch (Exception e) {
                    System.out.println("El ID no corresponde a ningun cliente en el sistema");
                }

                System.out.println("Ingresar la fecha de prestamo:");
                System.out.println("Anio:");
                int anio = leer.nextInt();
                System.out.println("Mes:");
                int mes = leer.nextInt();
                System.out.println("Dia:");
                int dia = leer.nextInt();
                LocalDate fechaprestamo = LocalDate.of(anio, mes, dia);
                LocalDate fechadevolucion = fechaprestamo.plusDays(15);

                prestamo.setFechaPrestamo(fechaprestamo);
                prestamo.setFechaDevolucion(fechadevolucion);
                
                em.getTransaction().begin();
                em.persist(prestamo);
                em.getTransaction().commit();

            } catch (Exception e) {
            }
        } catch (Exception e) {
        }

    }

    public void buscarPrestamosXCliente() {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                System.out.println("Ingresar el ID del cliente:");
                String id = leer.next();
                List<prestamo> prestamos = em.createQuery("SELECT p FROM cliente c, prestamo p WHERE c.id = '" + id + "'").getResultList();
                prestamos.forEach((aux) -> {
                    System.out.println(aux);
                });

            } catch (Exception e) {
                System.out.println("No existen prestamos realizados a nombre del cliente");
            }
        } catch (Exception e) {
        }
    }

}
