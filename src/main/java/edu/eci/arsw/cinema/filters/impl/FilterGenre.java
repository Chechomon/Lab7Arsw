package edu.eci.arsw.cinema.filters.impl;


import edu.eci.arsw.cinema.filters.Filter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class FilterGenre implements Filter {
    @Override
    public List<Movie> filter(Cinema cinema, String date, String filter) {
        List<Movie> movies = new ArrayList<>();
        for(CinemaFunction cf : cinema.getFunctions()){
            if(cf.getMovie().getGenre().equals(filter)) movies.add(cf.getMovie());
        }
        return movies;
    }
}
