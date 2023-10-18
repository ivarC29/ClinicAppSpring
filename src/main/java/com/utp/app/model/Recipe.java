package com.utp.app.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
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
	@JsonIgnore
    private List<Medicine> medicines;
	
	@OneToMany(mappedBy="recipe" , cascade=CascadeType.ALL)
	@JsonIgnore
	private List<RecipeDetail> recipeDetails;


}
