package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.model.Animal;
import com.lambdaschool.zoos.view.AnimalCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    Animal findByAnimaltype(String type);

    @Query(value = "SELECT animaltype, count(a.animalid) as count FROM animal a JOIN zooanimals z ON a.animalID=z.animalid GROUP BY a.animalid", nativeQuery = true)
    List<AnimalCount> countAnimals();

}