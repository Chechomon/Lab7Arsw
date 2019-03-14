/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.model.Seat;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cristian
 */
@Service
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),"2018-12-18 15:30");
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),"2018-13-18 15:30");
        CinemaFunction funct11 = new CinemaFunction(new Movie("Die Hard","Action"),"2018-14-18 15:30");
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Grudge","Horror"),"2018-15-18 15:30");
        functions.add(funct1);
        functions.add(funct2);
        functions2.add(funct11);
        functions2.add(funct22);
        Cinema c=new Cinema("cinemaX",functions);
        Cinema d=new Cinema("cinemaY",functions2);
        cinemas.put("cinemaX", c);
        cinemas.put("cinemaY", d);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        List<CinemaFunction> functions = getFunctionsbyCinemaAndDate(cinema,date);
        CinemaFunction function = null;
        for(CinemaFunction cf : functions){
            if(cf.getMovie().getName().equals(movieName)) function = cf;
        }
        if(function == null) throw new CinemaException("There is no function with that movie or date");
        function.buyTicket(row,col);
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> functions = cinemas.get(cinema).getFunctions();
        List<CinemaFunction> functions2 = new ArrayList<>();
        for(CinemaFunction cf : functions){
            if(cf.getDate().equals(date)) functions2.add(cf);
        }
        return functions2;
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return cinemas.get(name);
    }

    @Override
    public Map<String,Cinema> getAllCinemas() {
        return cinemas;
    }
    
    @Override
    public List<CinemaFunction> getFunctionsbyCinema(String cinema) {
        List<CinemaFunction> functions = cinemas.get(cinema).getFunctions();
        return functions;
    }
    
    @Override
    public void addFunction(String name, CinemaFunction cf) throws CinemaPersistenceException {
        Cinema c = getCinema(name);
        c.addFunction(cf);
    }
    
    @Override
    public void updateFunction(String name, CinemaFunction cf) throws CinemaPersistenceException {
        Cinema c = getCinema(name);
        c.updateFunction(cf);
    }
}
