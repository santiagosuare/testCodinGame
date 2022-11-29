package com.plexus.testCodinGame.controllers;


import com.plexus.testCodinGame.config.TimerConfig;
import com.plexus.testCodinGame.model.entitys.SuperHeroes;
import com.plexus.testCodinGame.model.service.SuperHeroesService;
import com.plexus.testCodinGame.repository.SuperHeroesRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/v1")
public class SuperHeroesController {

    private final static Logger Log = LoggerFactory.getLogger(SuperHeroesController.class);

    @Autowired
    SuperHeroesRepository superHeroesRepository;

    @Autowired
    SuperHeroesService superHeroesService;

    TimerConfig timerConfig;

    public SuperHeroesController(TimerConfig timerConfig, SuperHeroesRepository superHeroesRepository, SuperHeroesService superHeroesService) {
        this.timerConfig = timerConfig;
        this.superHeroesRepository = superHeroesRepository;
        this.superHeroesService = superHeroesService;
    }

    @GetMapping("/heroes")
    @ApiOperation(value = "Get all Super Heroes from the database")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code= 409, message = "Conflict"),
    })
    public ResponseEntity<ArrayList<SuperHeroes>> getHeroes(){
        try {
            return  superHeroesService.getAllSuperHeroes();
        } catch ( Exception e ){
            Log.error("Error in getHeroesController: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/heroes/{id}")
    @Cacheable(value = "heroes")
    @ApiOperation(value = "Get the Super Hero via Id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code= 409, message = "Conflict"),
    })
    public ResponseEntity<SuperHeroes> getHeroesById(@PathVariable(name = "id") int id){
        try {
            return superHeroesService.getSuperHeroById(id);
        } catch (Exception e){
            Log.error("Error in getHeroesByIdController: " + e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/heroesWith/{string}")
    @ApiOperation(value = "Get a search for SuperHero")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code= 409, message = "Conflict"),
    })
    public ResponseEntity<List<SuperHeroes>> getHeroesByString (@PathVariable(name = "string") String withName){
        try {
            return superHeroesService.getSuperHeroesByPart(withName);
        } catch (Exception e ){
            Log.error("Error in getHeroesByStringController: " + e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/editSuperHeroes/")
    @ApiOperation(value = "Edit a SuperHero")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code= 409, message = "Conflict"),
    })
    public ResponseEntity<SuperHeroes> editSuperHero(@RequestBody SuperHeroes superHeroesBody){
        try {
            return superHeroesService.editSuperHero(superHeroesBody);
        } catch (Exception e ){
            Log.error("Error in editSuperHeroController: " + e );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/deleteSuperHeroes/")
    @ApiOperation(value = "Delete a SuperHero")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code= 409, message = "Conflict"),
    })
    public ResponseEntity<SuperHeroes> deleteSuperHero (@RequestBody SuperHeroes superHeroes){
        try {
            return superHeroesService.deleteSuperHero(superHeroes);
        } catch (Exception e){
            Log.error("Error in deleteSuperHeroController: " + e );
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
