package com.utp.app.model;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="recipe_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "recipeDetailId")
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
