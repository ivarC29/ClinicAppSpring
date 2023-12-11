package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.utp.app.model.RecipeDetail;
import com.utp.app.repository.RecipeDetailRepository;

@Service
public class RecipeDetailService {
	
	private RecipeDetailRepository recipeDetailRepository;
	
	public RecipeDetailService(RecipeDetailRepository recipeDetailRepository) {
		this.recipeDetailRepository = recipeDetailRepository;
	}

	public RecipeDetail saveRecipeDetail(RecipeDetail recipeDetail) {
		return recipeDetailRepository.save(recipeDetail);
	}
	
	public List<RecipeDetail> getRecipeDetails() {
		return recipeDetailRepository.findAll();
	}
	

}
