package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    private final Map<Integer, Animal> animals = new HashMap<>();

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerFullName;


    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animalList = new ArrayList<>(animals.values());
        return ResponseEntity.ok(animalList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable int id) {
        Animal animal = animals.get(id);

        if (animal == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(animal);
    }


    @PostMapping
    public ResponseEntity<Animal> addAnimal(
            @RequestParam int id,
            @RequestParam String name) {

        Animal animal = new Animal(id, name);
        animals.put(id, animal);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(animal);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(
            @PathVariable int id,
            @RequestBody Animal animal) {

        if (!animals.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        animals.put(id, animal);
        return ResponseEntity.ok(animal);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable int id) {
        Animal deletedAnimal = animals.remove(id);

        if (deletedAnimal == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deletedAnimal);
    }
}