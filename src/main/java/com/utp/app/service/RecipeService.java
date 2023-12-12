package com.utp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utp.app.model.Recipe;
import com.utp.app.repository.RecipeRepository;

@Service
public class RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	@Transactional
	public Recipe saveRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	@Transactional
	public Recipe getRecipeByAppointmentId(Long appointmentId) {
		return recipeRepository.findByAppointmentAppointmentId(appointmentId);
	}
	
	@Transactional
	public List<Recipe> getRecipesByPatientId(Long patientId) {
		return recipeRepository.findByPatientId(patientId);
	}
	
}
