package com.liberymutual.goforcode.rolodex.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator=ObjectIdGenerators.PropertyGenerator.class,
        property="id"
)

@Entity
public class PhoneNumber {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@JsonProperty("phone_number")
	@Column(length=5, nullable = false)
	private String phoneNumber;
	
	@JsonProperty("phone_type")
	@Column(length=75, nullable = false)
	private String type;

	@ManyToOne
	private Card card;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
