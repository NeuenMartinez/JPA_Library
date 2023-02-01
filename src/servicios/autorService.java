package servicios;

import entidades.autor;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author NeuenMartinez
 */
public class autorService {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void consularAutor() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                System.out.println("Ingrese el id del autor a buscar");
                Long id = leer.nextLong();

                autor a = em.find(autor.class, id);

                System.out.println(a);
            } catch (Exception e) {
                System.out.println("El id del autor no corresponde a ningun autor en el sistema");
            }
        } catch (Exception e) {
        }

    }

    public void añadirAutor() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPersistencia");
            EntityManager em = emf.createEntityManager();

            try {
                autor autor = new autor();
                System.out.println("Ingrese el nombre del autor");
                autor.setNombre(leer.next());

                autor.setAlta(true);

                autor.setId(null);
                //Iniciamos una transacción con el método getTransaction().begin();
                em.getTransaction().begin();
//Persistimos el objeto
                em.persist(autor);
//Terminamos la transacción con el método commit. Commit en programación significa confirmar un conjunto de cambios, en este caso persistir elobjeto 
                em.getTransaction().commit();
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
    }

    public void actualizarAutor() {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("Ingrese el id del autor a modificar");
        Long codigo = leer.nextLong();
        autor autor = em.find(autor.class, codigo);

        System.out.println("Ingresa el nombre que deseas asignar.");
        String nombre = leer.next();

//Le asignamos un nuevo nombre
        autor.setNombre(nombre);
        em.getTransaction().begin();
//Actualizamos el autor
        em.merge(autor);
        em.getTransaction().commit();
    }

    public void eliminarAutor() {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("ingrese el id del Autor a eliminar");

        Long codigo = leer.nextLong();

//Usamos el método find para buscar el autor a borrar
        autor autor = em.find(autor.class, codigo);
        em.getTransaction().begin();
//Borramos el autor
        em.remove(autor);
        em.getTransaction().commit();
    }

    public void consultarAutorXNombre(String nombre) {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

            try {
                List<autor> autores = em.createQuery("SELECT a FROM autor a WHERE a.nombre LIKE '" + nombre + "'").getResultList();

                for (autor aux : autores) {
            
                System.out.println("ID = " + aux.getId());
                System.out.println(aux.getNombre());
                
        }
                
            } catch (Exception e) {
                System.out.println("No existen autores con el nombre indicado");
            }
        } catch (Exception e) {
        }
    }
}
