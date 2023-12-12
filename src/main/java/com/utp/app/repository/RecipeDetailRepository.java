package com.utp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.RecipeDetail;

public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {
	
	public List<RecipeDetail> findByRecipeRecipeId(Long recipeId);

}
