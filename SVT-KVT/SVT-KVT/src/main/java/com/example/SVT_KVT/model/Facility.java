package com.example.SVT_KVT.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    private String address;
    private String city;
    private Double totalRating;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkDay> workDays = new ArrayList<>();

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discipline> disciplines = new ArrayList<>();

    @OneToMany(mappedBy = "facility")
    private List<Review> reviews = new ArrayList<>();

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkDay> workDays) {
        this.workDays = workDays;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addWorkDay(WorkDay workDay) {
        workDay.setFacility(this);
        this.workDays.add(workDay);
    }

    public void addDiscipline(Discipline discipline) {
        discipline.setFacility(this);
        this.disciplines.add(discipline);
    }
    
    public void calculateTotalRating() {
        if (reviews.isEmpty()) {
            this.totalRating = 0.0;
            return;
        }

        double totalRatingSum = 0.0;
        int count = 0;

        for (Review review : reviews) {
            Rate rate = review.getRate();
            if (rate != null) {
                double averageRating = (rate.getEquipment() + rate.getStaff() + rate.getHygiene() + rate.getSpace()) / 4.0;
                totalRatingSum += averageRating;
                count++;
            }
        }

        this.totalRating = count > 0 ? Math.round((totalRatingSum / count) * 10.0) / 10.0 : 0.0;
    }
}


