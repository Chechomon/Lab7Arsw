/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filters.Filter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.*;

/**
 *
 * @author cristian
 */
@Service
@Component("cinemaServices")
public class CinemaServices {

    @Autowired
    CinemaPersitence cps;

    @Autowired
    Filter fil;
    
    public void addNewCinema(Cinema c) throws CinemaPersistenceException {
        cps.saveCinema(c);
    }
    
    public Collection<Cinema> getAllCinemas(){
        return cps.getAllCinemas().values();
    }
    
    /**
     * 
     * @param name cinemas name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException, CinemaPersistenceException {
        return cps.getCinema(name);
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
         cps.buyTicket(row,col,cinema,date,movieName);
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        return cps.getFunctionsbyCinemaAndDate(cinema,date);
    }

    public List<Movie> filter(String cinema, String date, String filter) throws CinemaPersistenceException {
        return fil.filter(cps.getCinema(cinema),date,filter);
    }
    
    public List<CinemaFunction> getFunctionsbyCinema(String cinema){
        return cps.getFunctionsbyCinema(cinema);
    }
    
    public void addFunction(String name, CinemaFunction function) throws CinemaPersistenceException {
    	cps.addFunction(name, function);
    }
    
    public void updateFunction(String name, CinemaFunction cf) throws CinemaPersistenceException {
        cps.updateFunction(name, cf);
    }
}
