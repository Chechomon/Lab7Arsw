/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author cristian
 */
@RestController
@RequestMapping(value = "/cinemas")
@Service
public class CinemaAPIController {
    @Autowired
    CinemaServices cinemaServices;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        try {
            return new ResponseEntity<>(cinemaServices.getAllCinemas(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(edu.eci.arsw.cinema.controllers.CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaByName(@PathVariable String name){
        try {
            System.out.println(cinemaServices.getCinemaByName(name));
            return new ResponseEntity<>(cinemaServices.getCinemaByName(name), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(edu.eci.arsw.cinema.controllers.CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Cine no encontrado ",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{name}/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getFunctionsbyCinemaAndDate(@PathVariable String name, @PathVariable String date){
        try {
            return new ResponseEntity<>(cinemaServices.getFunctionsbyCinemaAndDate(name, date), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(edu.eci.arsw.cinema.controllers.CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Funcion no encontrada ",HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/{name}/{date}/{moviename}", method = RequestMethod.GET)
    public ResponseEntity<?> getMoviebyCinemaAndDate(@PathVariable String name, @PathVariable String date, @PathVariable String moviename) throws CinemaPersistenceException, CinemaException {
        try {
            CinemaFunction b = null;
            List<CinemaFunction> a = cinemaServices.getFunctionsbyCinemaAndDate(name, date);
            for (CinemaFunction i : a) {
                if (i.getMovie().getName().equals(moviename)) {
                    b = i;
                }
            }
            if (b == null) {
                throw new CinemaPersistenceException("No se encontro la funcion " + date);
            }
            return new ResponseEntity<>(b.getMovie(), HttpStatus.ACCEPTED);
        } catch (CinemaPersistenceException ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Funcion no encontrada", HttpStatus.NOT_FOUND);
        }

    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<?> postResourceCinemaByName(@RequestBody CinemaFunction function, @PathVariable String name) {
        try {
            cinemaServices.addFunction(name, function);
            return new ResponseEntity<>(cinemaServices.getCinemaByName(name), HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    public ResponseEntity<?> putResourceCinemaByName(@RequestBody CinemaFunction cf, @PathVariable String name) {
        try {
            cinemaServices.updateFunction(name, cf);
            return new ResponseEntity<>(cinemaServices.getCinemaByName(name), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
    
}
