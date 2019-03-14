package edu.eci.arsw.cinema.filters;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.Movie;

import java.util.List;

public interface Filter {
    List<Movie> filter(Cinema cinema, String date, String filter);
}
