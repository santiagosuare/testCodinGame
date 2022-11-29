package com.plexus.testCodinGame;

import com.plexus.testCodinGame.model.entitys.SuperHeroes;
import com.plexus.testCodinGame.model.service.SuperHeroesService;
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

	@Autowired
	SuperHeroesService superHeroesService;



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

		SuperHeroes superHeroes1 = new SuperHeroes(1, "Superman");
		SuperHeroes superHeroes2 = new SuperHeroes(2, "Batman");
		SuperHeroes superHeroes3 = new SuperHeroes(3, "Spiderman");
		SuperHeroes superHeroes4 = new SuperHeroes(4, "Iron man");
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


	@Test
	public void TestControllerSearchByString(){
		SuperHeroes superHeroes1 = new SuperHeroes(1, "Batman");
		SuperHeroes superHeroes2 = new SuperHeroes(2, "Superman");
		SuperHeroes superHeroes3 = new SuperHeroes(3, "Spiderman");
		SuperHeroes superHeroes4 = new SuperHeroes(4, "Iron man");
		ArrayList<SuperHeroes> superHeroesList = new ArrayList<>();

		superHeroesList.add(superHeroes1);
		superHeroesList.add(superHeroes2);
		superHeroesList.add(superHeroes3);
		superHeroesList.add(superHeroes4);

		superHeroesRepository.saveAll(superHeroesList);

		List<SuperHeroes> superHeroesListFind = superHeroesRepository.findByNameContaining("Iron man");
		List<SuperHeroes> superHeroesListFind1 = superHeroesRepository.findByNameContaining("man");

		Assertions.assertEquals(superHeroes4.getName(), superHeroesListFind.get(0).getName());

		Assertions.assertEquals(superHeroesList.get(0).getName(), superHeroesListFind1.get(0).getName());
		Assertions.assertEquals(superHeroesList.get(1).getName(), superHeroesListFind1.get(1).getName());
		Assertions.assertEquals(superHeroesList.get(2).getName(), superHeroesListFind1.get(2).getName());
		Assertions.assertEquals(superHeroesList.get(3).getName(), superHeroesListFind1.get(3).getName());

	}

	@Test
	public void TestControllerDeleteSuperHero(){
		SuperHeroes superHeroes1 = new SuperHeroes(1, "Batman");
		SuperHeroes superHeroes2 = new SuperHeroes(2, "Superman");
		SuperHeroes superHeroes3 = new SuperHeroes(3, "Spiderman");
		SuperHeroes superHeroes4 = new SuperHeroes(4, "Iron man");
		ArrayList<SuperHeroes> superHeroesList = new ArrayList<>();

		superHeroesList.add(superHeroes1);
		superHeroesList.add(superHeroes2);
		superHeroesList.add(superHeroes3);
		superHeroesList.add(superHeroes4);

		superHeroesRepository.saveAll(superHeroesList);

		superHeroesService.deleteSuperHero(superHeroes3);

		List<SuperHeroes> superHeroesListFind = superHeroesRepository.findAll();

		Assertions.assertNotEquals(superHeroesListFind.size(), superHeroesList.size());
	}

	@Test
	public void TestControllerEditSuperHero(){
		SuperHeroes superHeroes = new SuperHeroes();
		superHeroes.setId(1);
		superHeroes.setName("Superman");

		superHeroesRepository.save(superHeroes);

		SuperHeroes superHeroesNew = new SuperHeroes();
		superHeroesNew.setId(1);
		superHeroesNew.setName("Batman");

		superHeroesService.editSuperHero(superHeroesNew);

		Optional<SuperHeroes> superHeroesOptional = superHeroesRepository.findById(1L);

		Assertions.assertEquals(superHeroesOptional.get().getName(), superHeroesNew.getName());
	}

}
