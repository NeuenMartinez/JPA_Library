package servicios;

import entidades.cliente;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author NeuenMartinez
 */
public class clienteService {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void crearCliente() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                cliente cliente = new cliente();
                System.out.println("Ingresar el DNI del cliente:");
                cliente.setDNI(leer.nextLong());
                System.out.println("Ingresar el nombre del cliente:");
                cliente.setNombre(leer.next());
                System.out.println("Ingresar el apellido del cliente:");
                cliente.setApellido(leer.next());
                System.out.println("Ingresar el telefono del cliente:");
                cliente.setTelefono(leer.next());
                em.getTransaction().begin();
                em.persist(cliente);
                em.getTransaction().commit();

            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }

    public void modificarCliente() {

        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("Ingresar el ID del cliente a modificar:");
        Long codigo = leer.nextLong();
        cliente c = em.find(cliente.class, codigo);
        
        System.out.println("Ingresar el nuevo DNI del cliente:");
        c.setDNI(leer.nextLong());
        System.out.println("Ingresar el nuevo nombre del cliente:");
        c.setNombre(leer.next());
        System.out.println("Ingresar el nuevo apellido del cliente:");
        c.setApellido(leer.next());
        System.out.println("Ingresar el nuevo telefono del cliente:");
        c.setTelefono(leer.next());
    }
    
    public void eliminarCliente(){
        
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("ingrese el id del cliente a eliminar");

        Long codigo = leer.nextLong();

        cliente c = em.find(cliente.class, codigo);
        
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }
    
    public void buscarCliente(){
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                System.out.println("Ingrese el id del cliente a buscar");
                Long id = leer.nextLong();

                cliente c = em.find(cliente.class, id);

                System.out.println(c);
            } catch (Exception e) {
                System.out.println("El id del cliente no corresponde a ningun autor en el sistema");
            }
        } catch (Exception e) {
        }
    }

}
