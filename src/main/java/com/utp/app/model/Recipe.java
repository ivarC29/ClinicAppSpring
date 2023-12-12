package com.utp.app.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "recipeId")
public class Recipe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
    private Long recipeId;

	@Column(name = "recipe_date")
	private Date recipeDate;

	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;

	@ManyToMany
    @JoinTable(
        name = "recipe_detail",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;
	
	@OneToMany(mappedBy="recipe" , cascade=CascadeType.ALL)
	private List<RecipeDetail> recipeDetails;

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public Date getRecipeDate() {
		return recipeDate;
	}

	public void setRecipeDate(Date recipeDate) {
		this.recipeDate = recipeDate;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<RecipeDetail> getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(List<RecipeDetail> recipeDetails) {
		this.recipeDetails = recipeDetails;
	}
}
