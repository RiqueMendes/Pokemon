package com.poke.study.Controller;

import java.util.List;

import com.poke.study.Model.Trainer;
import com.poke.study.Repository.TrainerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/trainers")
public class TrainerController {


    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping
    public List<Trainer> trainerList(){
        return trainerRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getByIdTrainer(@PathVariable Long id){
        return trainerRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Trainer>>getAllPokemonByName(@PathVariable String name){
        if(trainerRepository.findAllByNameContainingIgnoreCase(name).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trainers with this name found");
		}
		return ResponseEntity.ok(trainerRepository.findAllByNameContainingIgnoreCase(name));
	}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainer addTrainer (@RequestBody Trainer trainer){
        return trainerRepository.save(trainer);

    }

    @DeleteMapping("/{id}")
	public void deleteTrainer(@PathVariable Long id) {
		trainerRepository.deleteById(id);
	}
    @PutMapping("/update/{id}")
	public Trainer update (@RequestBody Trainer updateTrainer){
        return trainerRepository.save(updateTrainer);

    }

}   
