package com.methaporce.vista;

import com.methaporce.modelo.GestorPelicula;
import com.methaporce.modelo.Pelicula;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adri
 */
public class Main {

    public static void main(String[] args) {

        // Lista de peliculas
        List<Pelicula> lp = new ArrayList<>();
        lp.add(new Pelicula(1, "El Castillo Vagabundo", true));
        lp.add(new Pelicula(2, "Cruella", false));
        lp.add(new Pelicula(3, "La Casa de Cera", true));
        lp.add(new Pelicula(4, "El Pequeño Mundo de Arrieti", true));
        lp.add(new Pelicula(5, "La Monja", false));

        GestorPelicula gp = new GestorPelicula(lp);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Agregar película");
            System.out.println("2. Eliminar película");
            System.out.println("3. Mostrar lista de películas");
            System.out.println("4. Mostrar películas disponibles");
            System.out.println("5. Mostrar películas no disponibles");
            System.out.println("6. Marcar película como disponible");
            System.out.println("0. Salir");
            System.out.println("");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            System.out.println("");

            switch (opcion) {

                case 1:

//                    // Crear instancias de películas
//                    Pelicula p1 = new Pelicula(6, "El Extraño Mundo de Jack", true);
//                    Pelicula p2 = new Pelicula(7, "El Cadaver de la Novia", false);
//
//                    // Agregar películas al gestor
//                    gp.agregarPelicula(p1);
//                    System.out.println("Se agrego la pelicula: " + p1.getNombre());
//                    gp.agregarPelicula(p2);
//                    System.out.println("Se agrego la pelicula: " + p2.getNombre());
//                    System.out.println("******************");
//                    System.out.println("");
                    // Agregar película
                    System.out.print("Ingrese el Id de la película: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nombre de la película: ");
                    String nombre = scanner.nextLine();
                    System.out.print("¿La película está disponible? (true/false): ");
                    boolean disponible = scanner.nextBoolean();
                    scanner.nextLine();

                    Pelicula pelicula = new Pelicula(id, nombre, disponible);
                    gp.agregarPelicula(pelicula);
                    System.out.println("Pelicula agregada con éxito.");
                    System.out.println("******************");
                    System.out.println("");
                    break;

                case 2:

                    // Eliminar película
                    System.out.print("Ingrese el Id de la película que desea eliminar: ");
                    int id2 = scanner.nextInt();
                    scanner.nextLine();
                    gp.eliminarPelicula(id2);
                    System.out.println("Pelicula eliminada con éxito.");
                    System.out.println("******************");
                    System.out.println("");
                    break;

                case 3:

                    // Mostrar lista de películas
                    System.out.println("Todas las películas:");
                    for (Pelicula p : gp.obtenerPeliculas()) {
                        System.out.println("Id: " + p.getId() + ", Nombre: " + p.getNombre() + ", ¿Esta disponible?: " + p.isDisponible());
                        System.out.println("******************");
                    }
                    System.out.println("");
                    break;

                case 4:

                    // Mostrar películas disponibles
                    System.out.println("Películas disponibles:");
                    for (Pelicula p : gp.obtenerPeliculasDisponibles()) {
                        System.out.println("Id: " + p.getId() + ", Nombre: " + p.getNombre());
                        System.out.println("******************");
                    }
                    System.out.println("");
                    break;

                case 5:

                    // Mostrar películas no disponibles
                    System.out.println("Películas disponibles:");
                    for (Pelicula p : gp.obtenerPeliculasNoDisponibles()) {
                        System.out.println("Id: " + p.getId() + ", Nombre: " + p.getNombre());
                        System.out.println("******************");
                    }
                    System.out.println("");
                    break;

                case 6:

                    // Marcar película como disponible
                    System.out.print("Ingrese el Id de la película que desea marcar como disponible: ");
                    int id3 = scanner.nextInt();
                    scanner.nextLine();
                    gp.marcarPeliculaComoDisponible(id3);
                    System.out.println("La película ya se encuentra disponible.");
                    System.out.println("******************");
                    System.out.println("");
                    break;

                case 0:

                    // Salir del programa
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:

                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        }
    }
}
