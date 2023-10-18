package com.utp.app.model;

import java.sql.Time;

import jakarta.persistence.*;

@Entity
@Table(name="recipe_detail")
public class RecipeDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_detail_id")
	private Long		recipeDetailId;
	
	@ManyToOne
    @JoinColumn(name = "medicine_id")
	private Medicine	medicine;
	
	@ManyToOne
    @JoinColumn(name = "recipe_id")
	private Recipe		recipe;
	
	private Time		interval;
	
	private Integer		dosage;

	public Long getRecipeDetailId() {
		return recipeDetailId;
	}
	public void setRecipeDetailId(Long recipeDetailId) {
		this.recipeDetailId = recipeDetailId;
	}
	public Time getInterval() {
		return interval;
	}
	public void setInterval(Time interval) {
		this.interval = interval;
	}
	public Integer getDosage() {
		return dosage;
	}
	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
