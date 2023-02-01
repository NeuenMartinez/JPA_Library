package servicios;

import entidades.editorial;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author NeuenMartinez
 */
public class editorialService {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    public void consularEditorial() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                System.out.println("Ingrese el id de la editorial a buscar");
                Long id = leer.nextLong();

                editorial a = em.find(editorial.class, id);

                System.out.println(a);
            } catch (Exception e) {
                System.out.println("El id de la editorial no corresponde a ninguna editorial en el sistema");
            }
        } catch (Exception e) {
        }

    }

    public void añadirEditorial() {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPersistencia");
            EntityManager em = emf.createEntityManager();

            try {
                editorial editorial = new editorial();

                System.out.println("Ingrese el nombre de la editorial");

                editorial.setNombre(leer.next());

                editorial.setAlta(true);
                
                editorial.setId(null);

                //Iniciamos una transacción con el método getTransaction().begin();
                em.getTransaction().begin();
                //Persistimos el objeto
                em.persist(editorial);
                //Terminamos la transacción con el método commit. Commit en programación significa confirmar un conjunto de cambios, en este caso persistir elobjeto 
                em.getTransaction().commit();
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
    }

    public void actualizarEditorial() {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("Ingrese el id de la editorial a modificar");
        Long codigo = leer.nextLong();
        editorial editorial = em.find(editorial.class, codigo);

        System.out.println("Ingresa el nombre que deseas asignar.");
        String nombre = leer.next();

//Le asignamos un nuevo nombre
        editorial.setNombre(nombre);
        em.getTransaction().begin();
//Actualizamos la editorial
        em.merge(editorial);
        em.getTransaction().commit();
    }

    public void eliminarEditorial() {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("ingrese el id de la editorial a eliminar");

        Long codigo = leer.nextLong();

//Usamos el método find para buscar la editorial a borrar
        editorial editorial = em.find(editorial.class, codigo);
        em.getTransaction().begin();
//Borramos el editorial.
        em.remove(editorial);
        em.getTransaction().commit();
    }

}
