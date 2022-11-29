package com.plexus.testCodinGame.repository;

import com.plexus.testCodinGame.model.entitys.SuperHeroes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface SuperHeroesRepository extends JpaRepository<SuperHeroes, Long> {

    List<SuperHeroes> findByNameContaining(String name);
}
