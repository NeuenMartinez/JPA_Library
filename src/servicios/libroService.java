package servicios;

import entidades.autor;
import entidades.editorial;
import entidades.libro;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author NeuenMartinez
 */
public class libroService {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void añadirLibro() {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPersistencia");
            EntityManager em = emf.createEntityManager();

            try {
                libro libro = new libro();

                System.out.println("Ingrese el titulo del libro");

                libro.setNombre(leer.next());

                System.out.println("ingresa el año del libro.");

                libro.setAnio(leer.nextInt());

                System.out.println("Ingresa la cantidad de ejemplares impresos.");

                libro.setEjemplares(leer.nextInt());

                System.out.println("Ingresa la cantidad de ejemplares prestados.");

                libro.setEjemplaresPrestados(leer.nextInt());

                libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());

                libro.setAlta(true);

                libro.setIsbn(null);

                System.out.println("ingrese el ID del autor");

                int id = leer.nextInt();

                libro.setAutor(em.find(autor.class, id));

                System.out.println("ingrese el ID de la editorial");

                id = leer.nextInt();

                libro.setEditorial(em.find(editorial.class, id));
                /*solucion simple al problema de relacionar las clases en el servicio de libro usar el id seria mas optimo pero no lo pense cuando lo escribi se recomienda agregar ayuda visual para el usuario este sistema se rompe el momento que lo inicializas antes que las otras tablas asi que tenerlo en cuenta
                 */

                //Iniciamos una transacción con el método getTransaction().begin();
                em.getTransaction().begin();
                //Persistimos el objeto
                em.persist(libro);
                //Terminamos la transacción con el método commit. Commit en programación significa confirmar un conjunto de cambios, en este caso persistir elobjeto 
                em.getTransaction().commit();
            } catch (Exception e) {
            }

        } catch (Exception e) {
        }
    }

    public void actualizarLibro() {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("Ingrese el codigo ISBN del libro a modificar");
        Long codigo = leer.nextLong();
        libro libro = em.find(libro.class, codigo);

        try {

            System.out.println("Ingrese el titulo del libro");

            libro.setNombre(leer.next());

            System.out.println("ingresa el año del libro.");

            libro.setAnio(leer.nextInt());

            System.out.println("Ingresa la cantidad de ejemplares impresos.");

            libro.setEjemplares(leer.nextInt());

            System.out.println("Ingresa la cantidad de ejemplares prestados.");

            libro.setEjemplaresPrestados(leer.nextInt());

            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());

            System.out.println("ingrese el ID del autor");

            int id = leer.nextInt();

            libro.setAutor(em.find(autor.class, id));

            System.out.println("ingrese el ID de la editorial");

            id = leer.nextInt();

            libro.setEditorial(em.find(editorial.class, id));
            /*solucion simple al problema de relacionar las clases en el servicio de libro usar el id seria mas optimo pero no lo pense cuando lo escribi se recomienda agregar ayuda visual para el usuario este sistema se rompe el momento que lo inicializas antes que las otras tablas asi que tenerlo en cuenta
             */

            //Iniciamos una transacción con el método getTransaction().begin();
            em.getTransaction().begin();
            //Persistimos el objeto
            em.persist(libro);
            //Terminamos la transacción con el método commit. Commit en programación significa confirmar un conjunto de cambios, en este caso persistir elobjeto 
            em.getTransaction().commit();
        } catch (Exception e) {
        }

    }

    public void eliminarLibro() {
        EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

        System.out.println("ingrese el id del libro a eliminar");

        Long codigo = leer.nextLong();

        //Usamos el método find para buscar el libro a borrar
        libro libro = em.find(libro.class, codigo);
        em.getTransaction().begin();

        //Borramos el libro
        em.remove(libro);
        em.getTransaction().commit();
    }
    
    public void consularLibroXIsbn() {
        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();
            try {
                System.out.println("Ingrese el codigo ISBN del libro a buscar");
                Long codigo = leer.nextLong();

                libro a = em.find(libro.class, codigo);

                System.out.println(a);
            } catch (Exception e) {
                System.out.println("El codigo ISBN no corresponde a ningun libro en el sistema");
            }
        } catch (Exception e) {
        }

    }

    public void consultarLibroXTirulo(String titulo) {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

            try {
                List<libro> libros = em.createQuery("SELECT a FROM libro a WHERE a.nombre LIKE '" + titulo + "'").getResultList();
                for (libro aux : libros) {
            
                System.out.println("ISBN = " + aux.getIsbn());
                System.out.println(aux.getNombre());
                System.out.println(aux.getAnio());

                if (aux.isAlta() == true) {
                    System.out.println("El libro está disponible.");
                } else {
                    System.out.println("El libro no está disponible.");
                }

                System.out.println("Autor: " + aux.getAutor());
                System.out.println("Hay un total de " + aux.getEjemplares() + " ejemplares");
                System.out.println(aux.getEjemplaresPrestados() + " son prestados");
                System.out.println(aux.getEjemplaresRestantes() + " son los que quedan disponibles");
                System.out.println("Editorial " + aux.getEditorial());
                System.out.println("");
        }

            } catch (Exception e) {
                System.out.println("No existen libros con el titulo indicado");
            }
        } catch (Exception e) {
        }

    }

    public void consultarLibroXNombreAutor(String nombre) {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

            try {
                List<libro> libros = em.createQuery("SELECT a FROM libro a WHERE a.autor.nombre LIKE '" + nombre + "'").getResultList();

                for (libro aux : libros) {
            
                System.out.println("ISBN = " + aux.getIsbn());
                System.out.println(aux.getNombre());
                System.out.println(aux.getAnio());

                if (aux.isAlta() == true) {
                    System.out.println("El libro está disponible.");
                } else {
                    System.out.println("El libro no está disponible.");
                }

                System.out.println("Autor: " + aux.getAutor());
                System.out.println("Hay un total de " + aux.getEjemplares() + " ejemplares");
                System.out.println(aux.getEjemplaresPrestados() + " son prestados");
                System.out.println(aux.getEjemplaresRestantes() + " son los que quedan disponibles");
                System.out.println("Editorial " + aux.getEditorial());
                System.out.println("");
        }
            } catch (Exception e) {
                System.out.println("No existen libros con el nombre de autor indicado");
            }
        } catch (Exception e) {
        }
    }

    public void consultarLibroXNombreEditorial(String nombre) {

        try {
            EntityManager em = Persistence.createEntityManagerFactory("LibreriaPersistencia").createEntityManager();

            try {
                List<libro> libros = em.createQuery("SELECT a FROM libro a WHERE a.editorial.nombre LIKE '" + nombre + "'").getResultList();

                for (libro aux : libros) {
            
                System.out.println("ISBN = " + aux.getIsbn());
                System.out.println(aux.getNombre());
                System.out.println(aux.getAnio());

                if (aux.isAlta() == true) {
                    System.out.println("El libro está disponible.");
                } else {
                    System.out.println("El libro no está disponible.");
                }

                System.out.println("Autor: " + aux.getAutor());
                System.out.println("Hay un total de " + aux.getEjemplares() + " ejemplares");
                System.out.println(aux.getEjemplaresPrestados() + " son prestados");
                System.out.println(aux.getEjemplaresRestantes() + " son los que quedan disponibles");
                System.out.println("Editorial " + aux.getEditorial());
                System.out.println("");
        }
            } catch (Exception e) {
                System.out.println("No existen libros con el nombre de editorial indicado");
            }
        } catch (Exception e) {
        }
    }
    }
