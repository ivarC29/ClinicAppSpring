package com.utp.app.model;

public enum MedicalSpeciality {
	
	CARDIOLOGIA("Cardiología"),
    DERMATOLOGIA("Dermatología"),
    PEDIATRIA("Pediatría"),
    GINECOLOGIA("Ginecología"),
    ORTOPEDIA("Ortopedia"),
    OTROS("Otros");

    private String description;

    MedicalSpeciality(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
