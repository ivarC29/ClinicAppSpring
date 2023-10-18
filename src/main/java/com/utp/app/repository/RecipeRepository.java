package com.utp.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.utp.app.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
