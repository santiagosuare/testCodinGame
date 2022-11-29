package com.plexus.testCodinGame.model.service;

import com.plexus.testCodinGame.model.entitys.SuperHeroes;
import com.plexus.testCodinGame.model.service.exception.BadRequestException;
import com.plexus.testCodinGame.repository.SuperHeroesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SuperHeroesService {

    @Autowired
    SuperHeroesRepository superHeroesRepository;

    private final static Logger Log = LoggerFactory.getLogger(SuperHeroesService.class);


    public ResponseEntity<ArrayList<SuperHeroes>> getAllSuperHeroes(){
        Iterable<SuperHeroes> superHeroesIterable = superHeroesRepository.findAll();
        ArrayList<SuperHeroes> superHeroesArrayList = new ArrayList<>();
        superHeroesIterable.forEach(superHeroesArrayList::add);
        return ResponseEntity.ok().body(superHeroesArrayList);
    }

    public ResponseEntity<SuperHeroes> getSuperHeroById(int id){
        Optional<SuperHeroes> superHeroesOptional = superHeroesRepository.findById(Long.valueOf(id));
        if (superHeroesOptional.isPresent()){
            SuperHeroes superHeroes = superHeroesOptional.get();
            return ResponseEntity.ok().body(superHeroes);
        } else {
            Log.warn("Not Found the superHero for the id: {}", id);
            throw new BadRequestException("Not Found the superHero for the id");
        }
    }

    public ResponseEntity<List<SuperHeroes>> getSuperHeroesByPart(String withName){
        List<SuperHeroes> superHeroesList = superHeroesRepository.findByNameContaining(withName.toUpperCase());
        return ResponseEntity.ok().body(superHeroesList);
    }

    public ResponseEntity<SuperHeroes> editSuperHero(SuperHeroes superHeroesBody) {
        Optional<SuperHeroes> superHeroesOptional = superHeroesRepository.findById(superHeroesBody.getId());
        if (superHeroesOptional.isPresent()) {
            SuperHeroes superHeroes = superHeroesOptional.get();
            superHeroes.setName(superHeroesBody.getName());
            superHeroesRepository.save(superHeroes);
            return ResponseEntity.ok().body(superHeroes);
        } else {
            Log.warn("Not Found the superHero: {}", superHeroesBody);
            throw new BadRequestException("Not Found the superHero");
        }

    }

    public ResponseEntity<SuperHeroes> deleteSuperHero (SuperHeroes superHeroes){
        Optional<SuperHeroes> superHeroesOptional = superHeroesRepository.findById(superHeroes.getId());
        if (superHeroesOptional.isPresent()){
            SuperHeroes superHeroesToDelete = superHeroesOptional.get();
            superHeroesRepository.delete(superHeroesToDelete);
            return ResponseEntity.ok().body(superHeroesToDelete);
        } else {
            Log.warn("Not Found the superHero: {}", superHeroes);
            throw new BadRequestException("Not Found the superHero");
        }
    }

}
