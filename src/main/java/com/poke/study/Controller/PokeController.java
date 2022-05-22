package com.poke.study.Controller;

import java.util.List;

import com.poke.study.Model.Pokemon;
import com.poke.study.Repository.PokeRepository;

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
@RequestMapping("/pokemons")
public class PokeController {



    @Autowired
    private PokeRepository pokeRepository;

    @GetMapping
    public List<Pokemon> pokelist(){
        return pokeRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getByIdPokemon(@PathVariable Long id){
        return pokeRepository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Pokemon>>getAllPokemonByType(@PathVariable String type){
        if(pokeRepository.findAllByTypeContainingIgnoreCase(type).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemons with this type found");
		}
		return ResponseEntity.ok(pokeRepository.findAllByTypeContainingIgnoreCase(type));
	}

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Pokemon>>getAllPokemonByName(@PathVariable String name){
        if(pokeRepository.findAllByNameContainingIgnoreCase(name).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemons with this name found");
		}
		return ResponseEntity.ok(pokeRepository.findAllByNameContainingIgnoreCase(name));
	}
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon add (@RequestBody Pokemon pokemon){
        return pokeRepository.save(pokemon);

    }


    @DeleteMapping("/{id}")
	public void deletePoke(@PathVariable Long id) {
		pokeRepository.deleteById(id);
	}

    @PutMapping("/update/{id}")
	public Pokemon update (@RequestBody Pokemon updateObj){
        return pokeRepository.save(updateObj);

    }

		

}
    

