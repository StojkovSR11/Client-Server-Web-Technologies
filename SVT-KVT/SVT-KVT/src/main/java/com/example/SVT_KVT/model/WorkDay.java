package com.example.SVT_KVT.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date validFrom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day day;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date fromTime;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date untilTime;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getUntilTime() {
		return untilTime;
	}

	public void setUntilTime(Date untilTime) {
		this.untilTime = untilTime;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

    // Getters and Setters
    
}
