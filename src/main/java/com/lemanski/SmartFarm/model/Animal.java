package com.lemanski.SmartFarm.model;


import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String gender;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate purchaseDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate dischargeDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate birthDate;

    private Double purchaseCost;

    @NotNull(message = "Animal race is required.")
    private String race;

    @Size(max = 2)
    private String originCountry;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    private List<Treatment> treatments;

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

    @PrePersist
    protected void onCreate() {
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = LocalDateTime.now();
    }
}
