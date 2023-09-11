package com.methaporce.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adri
 */
public class GestorPelicula {

    private List<Pelicula> lp;

    public GestorPelicula(List<Pelicula> peliculas) {
        //ListaPeliculas = new ArrayList<>();
        lp = peliculas;
    }

    public void agregarPelicula(Pelicula pelicula) {
        lp.add(pelicula);
    }

    public void eliminarPelicula(int id) {
        lp.removeIf(pelicula -> pelicula.getId() == id);
    }

    public List<Pelicula> obtenerPeliculas() {
        return new ArrayList<>(lp);
    }

    public List<Pelicula> obtenerPeliculasDisponibles() {
        List<Pelicula> disponibles = new ArrayList<>();
        for (Pelicula pelicula : lp) {
            if (pelicula.isDisponible()) {
                disponibles.add(pelicula);
            }
        }
        return disponibles;
    }

    public List<Pelicula> obtenerPeliculasNoDisponibles() {
        List<Pelicula> noDisponibles = new ArrayList<>();
        for (Pelicula pelicula : lp) {
            if (!pelicula.isDisponible()) {
                noDisponibles.add(pelicula);
            }
        }
        return noDisponibles;
    }

    public void marcarPeliculaComoDisponible(int id) {
        for (Pelicula pelicula : lp) {
            if (pelicula.getId() == id) {
                pelicula.setDisponible(true);
                return;
            }
        }
    }

}
