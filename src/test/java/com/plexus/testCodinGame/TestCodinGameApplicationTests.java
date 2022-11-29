package com.plexus.testCodinGame;

import com.plexus.testCodinGame.model.entitys.SuperHeroes;
import com.plexus.testCodinGame.repository.SuperHeroesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TestCodinGameApplicationTests {

	@Autowired
	SuperHeroesRepository superHeroesRepository;



	@Test
	void contextLoads() {
	}


	@Test
	public void TestControllerFindById(){
		SuperHeroes superHeroes = new SuperHeroes();
		superHeroes.setId(1);
		superHeroes.setName("Superman");

		superHeroesRepository.save(superHeroes);

		Optional<SuperHeroes> superHeroesFind = superHeroesRepository.findById(superHeroes.getId());

		Assertions.assertEquals(superHeroesFind.get().getId(), superHeroes.getId() );
		Assertions.assertEquals(superHeroesFind.get().getName(), superHeroes.getName());

	}

	@Test
	public void TestControllerFindAll(){
		SuperHeroes superHeroes1 = new SuperHeroes(1, "Batman");
		SuperHeroes superHeroes2 = new SuperHeroes(2, "Superman");
		SuperHeroes superHeroes3 = new SuperHeroes(3, "Spiderman");
		SuperHeroes superHeroes4 = new SuperHeroes(4, "Iron Man");
		ArrayList<SuperHeroes> superHeroesList = new ArrayList<>();

		superHeroesList.add(superHeroes1);
		superHeroesList.add(superHeroes2);
		superHeroesList.add(superHeroes3);
		superHeroesList.add(superHeroes4);

		Iterable<SuperHeroes> superHeroesIterable = superHeroesRepository.saveAll(superHeroesList);

		ArrayList<SuperHeroes> superHeroesArrayList = new ArrayList<>();
		superHeroesIterable.forEach(superHeroesArrayList::add);

		List<SuperHeroes> superHeroesListFindAll = superHeroesRepository.findAll();

		Assertions.assertEquals(superHeroesArrayList.size(), superHeroesListFindAll.size());

	}



}
