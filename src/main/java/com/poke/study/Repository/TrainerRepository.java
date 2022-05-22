package com.poke.study.Repository;

import java.util.List;
import java.util.Optional;

import com.poke.study.Model.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    

    public List<Trainer>findAllByNameContainingIgnoreCase(String name);
    public List<Trainer>findAllByAge(Integer age);
    public Optional<Trainer> findByName(String name);
    public Optional<Trainer> findById(Integer id);



}
