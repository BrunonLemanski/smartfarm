package com.lemanski.SmartFarm.model;

public class AnimalTypeCounter {

    private AnimalType animalType;

    private Integer quantity;

    public AnimalTypeCounter(AnimalType animalType, Integer quantity) {
        this.animalType = animalType;
        this.quantity = quantity;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
