package com.utp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utp.app.model.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
