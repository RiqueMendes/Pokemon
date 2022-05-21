package com.poke.study.Repository;


import java.util.List;
import java.util.Optional;

import com.poke.study.Model.Pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokeRepository extends JpaRepository<Pokemon, Long> {

    public List<Pokemon> findAllByTypeContainingIgnoreCase(String type);
    public List<Pokemon>findAllByNameContainingIgnoreCase(String name);
    public Optional<Pokemon> findByName(String name);
    public Optional<Pokemon> findById(Integer id);



    
    
}
