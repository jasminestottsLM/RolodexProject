package com.liberymutual.goforcode.rolodex.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
        generator=ObjectIdGenerators.PropertyGenerator.class,
        property="id"
)

@Entity
public class Card {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@JsonProperty("first_name")
	@Column(length=75, nullable = false)
	private String firstName;
	
	@JsonProperty("last_name")
	@Column(length=75, nullable = false)
	private String lastName;
	
	@JsonProperty("person_title")
	@Column(length=75, nullable = false)
	private String title; 
	
	@JsonProperty("person_co")
	@Column(length=255, nullable = true)
	private String companyName;
	
	@OneToMany(mappedBy="card")
	private List<Address> addresses;
	
	@OneToMany(mappedBy="card")
	private List<PhoneNumber> phoneNumbers;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

//	public List<Address> getAddresses() {
//		return addresses;
//	}
//
//	public void setAddresses(List<Address> addresses) {
//		this.addresses = addresses;
//	}
//
//	public List<PhoneNumber> getPhoneNumbers() {
//		return phoneNumbers;
//	}
//
//	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
//		this.phoneNumbers = phoneNumbers;
//	}

	
}
