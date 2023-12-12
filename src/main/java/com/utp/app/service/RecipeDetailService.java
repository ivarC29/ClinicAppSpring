package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.RecipeDetail;
import com.utp.app.repository.RecipeDetailRepository;

@Service
public class RecipeDetailService {
	
	private RecipeDetailRepository recipeDetailRepository;
	
	public RecipeDetailService(RecipeDetailRepository recipeDetailRepository) {
		this.recipeDetailRepository = recipeDetailRepository;
	}

	@Transactional
	public RecipeDetail saveRecipeDetail(RecipeDetail recipeDetail) {
		return recipeDetailRepository.save(recipeDetail);
	}
	
	@Transactional
	public List<RecipeDetail> getRecipeDetails() {
		return recipeDetailRepository.findAll();
	}
	
	@Transactional
	public List<RecipeDetail> getRecipeDetailsByRecipeId(Long recipeId) {
		return recipeDetailRepository.findByRecipeRecipeId(recipeId);
	}
	

}
