package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.model.Animal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    Animal findByAnimaltype(String type);

    @Query(value = "SELECT a.ANIMALTYPE as animalname, count(z.ZOONAME) as zoocount FROM ZOO z JOIN ZOOANIMALS za ON z.ZOOID = za.ZOOID JOIN ANIMAL a ON a.ANIMALID = za.ANIMALID GROUP BY a.ANIMALTYPE", nativeQuery = true)
    ArrayList<CountAnimalsInZoo> queryCountAnimalsInZoo();
}