package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.RecipeDetail;

public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, Long> {

}
