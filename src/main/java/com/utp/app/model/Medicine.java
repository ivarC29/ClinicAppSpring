package com.utp.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

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

}
