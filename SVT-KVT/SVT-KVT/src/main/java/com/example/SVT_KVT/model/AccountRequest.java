package com.example.SVT_KVT.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AccountRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createdAt;

    private String address;

    @ManyToOne
    @JoinColumn(name = "status", nullable = true)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    // Getters and Setters
    
}
