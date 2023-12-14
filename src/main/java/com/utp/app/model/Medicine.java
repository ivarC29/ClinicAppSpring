package com.utp.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicines")
public class Medicine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medicine_id")
    private Long medicineId;
	
	@Column(name = "medicine_name")
    private String medicineName;

	@ManyToMany(mappedBy = "medicines")
	@JsonIgnore
    private List<Recipe> recipes;
    
	@OneToMany(mappedBy="medicine", cascade=CascadeType.ALL)
	@JsonIgnore
    private List<RecipeDetail> recipeDetails;

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public List<RecipeDetail> getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(List<RecipeDetail> recipeDetails) {
		this.recipeDetails = recipeDetails;
	}
}
