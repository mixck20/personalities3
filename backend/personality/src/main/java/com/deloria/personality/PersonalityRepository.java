package com.deloria.personality;

import org.springframework.data.repository.CrudRepository;

public interface PersonalityRepository extends CrudRepository<Personality, Integer> {
    // Add custom queries here if needed
}