package com.lambdaschool.zoos.service;

import com.lambdaschool.zoos.model.Animal;

import java.util.ArrayList;

public interface AnimalService
{
    ArrayList<Animal> findAll();

    Animal findAnimalByType(String type);

    ArrayList<CountAnimalsInZoo> getAnimalinZooCount();
}