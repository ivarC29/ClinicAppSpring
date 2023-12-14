package com.utp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.app.model.Recipe;
import com.utp.app.model.RecipeDetail;
import com.utp.app.service.RecipeDetailService;
import com.utp.app.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private RecipeDetailService recipeDetailService;

	@PutMapping("/add")
	public Recipe saveRecipe(@RequestBody Recipe recipe) {
		return recipeService.saveRecipe(recipe);
	}

	@GetMapping("/detail/{recipeId}")
	public List<RecipeDetail> getRecipeDetailByRecipeId(@PathVariable Long recipeId) {
		return recipeDetailService.getRecipeDetailsByRecipeId(recipeId);
	}

	@PutMapping("/detail/add")
	public RecipeDetail saveRecipeDetail(@RequestBody RecipeDetail recipeDetail) {
		return recipeDetailService.saveRecipeDetail(recipeDetail);
	}

	@GetMapping("/byAppointment/{appointmentId}")
	public Recipe getRecipeByAppointmentId(@PathVariable Long appointmentId) {
		return recipeService.getRecipeByAppointmentId(appointmentId);
	}
}
