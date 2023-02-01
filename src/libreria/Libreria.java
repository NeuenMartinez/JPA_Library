/*
 * @author NeuenMartinez
 */
package Libreria;

import java.util.Scanner;
import servicios.autorService;
import servicios.editorialService;
import servicios.libroService;

public class Libreria {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        libroService ls = new libroService();
        autorService as = new autorService();
        editorialService es = new editorialService();

        System.out.println("ingresando a la tabla de la libreria");
        bucleMenu:
        do {
            System.out.println("ingrese que desea hacer");
            System.out.println("1) Añadir libro" + "\n"
                    + "2) Modificar libro" + "\n"
                    + "3) Eliminar libro" + "\n"
                    + "4) Añadir autor" + "\n"
                    + "5) Modificar autor" + "\n"
                    + "6) Eliminar autor" + "\n"
                    + "7) Añadir editorial" + "\n"
                    + "8) Modificar editorial" + "\n"
                    + "9) Eliminar editorial" + "\n"
                    + "10)Búsqueda de un Autor por nombre." + "\n"
                    + "11)Búsqueda de un libro por ISBN." + "\n"
                    + "12)Búsqueda de un libro por Título." + "\n"
                    + "13)Búsqueda de un libro/s por nombre de Autor." + "\n"
                    + "14)Búsqueda de un libro/s por nombre de Editorial." + "\n"
                    + "15)Salir." + "\n");
            switch (leer.nextInt()) {
                case 1:
                    ls.añadirLibro();
                    break;
                case 2:
                    ls.actualizarLibro();
                    break;
                case 3:
                    ls.eliminarLibro();
                    break;
                case 4:
                    as.añadirAutor();
                    break;
                case 5:
                    as.actualizarAutor();
                    break;
                case 6:
                    as.eliminarAutor();
                    break;
                case 7:
                    es.añadirEditorial();
                    break;
                case 8:
                    es.actualizarEditorial();
                    break;
                case 9:
                    es.eliminarEditorial();
                    break;
                case 10:
                    System.out.println("Ingresar el nombre del autor:");
                    String nom = leer.next();
                    as.consultarAutorXNombre(nom);
                    break;
                case 11:
                    ls.consularLibroXIsbn();
                    break;
                case 12:
                    System.out.println("Ingresar el titulo del libro:");
                    ls.consultarLibroXTirulo(leer.next());
                    break;
                case 13:
                    System.out.println("Ingresar el nombre del autor:");
                    ls.consultarLibroXNombreAutor(leer.next());
                    break;
                case 14:
                    System.out.println("Ingresar el nombre de la editorial:");
                    ls.consultarLibroXNombreEditorial(leer.next());
                    break;
                case 15:
                    break bucleMenu;
                default:
                    System.out.println("ingrese un dato valido");

            }
        } while (true);
    }
}
