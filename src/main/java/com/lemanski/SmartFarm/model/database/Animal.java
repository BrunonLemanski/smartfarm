package com.lemanski.SmartFarm.model.database;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lemanski.SmartFarm.model.AnimalType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @NotNull(message = "Passport number is required.")
    @Column(unique = true)
    private String idPassport;

    @Size(max = 2)
    @NotNull(message = "Gender is required.")
    private String gender;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Animal type is required.")
    private AnimalType type; //cow, bull, calf

    private LocalDate purchaseDate;

    private LocalDate dischargeDate;

    private LocalDate birthDate;

    private Double purchaseCost;

    @NotNull(message = "Animal race is required.")
    private String race;

    @Size(max = 2)
    @NotNull(message = "Origin country is required.")
    private String originCountry;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal", orphanRemoval = true)
    private List<Treatment> treatments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal", orphanRemoval = true)
    private List<AnimalCost> animalCosts;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public Animal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPassport() {
        return idPassport;
    }

    public void setIdPassport(String idPassport) {
        this.idPassport = idPassport;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(LocalDate dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public List<AnimalCost> getAnimalCosts() {
        return animalCosts;
    }

    public void setAnimalCosts(List<AnimalCost> animalCosts) {
        this.animalCosts = animalCosts;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idPassport='" + idPassport + '\'' +
                ", gender='" + gender + '\'' +
                ", type=" + type +
                ", purchaseDate=" + purchaseDate +
                ", dischargeDate=" + dischargeDate +
                ", birthDate=" + birthDate +
                ", purchaseCost=" + purchaseCost +
                ", race='" + race + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}