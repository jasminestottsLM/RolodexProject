package com.liberymutual.goforcode.rolodex.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
	
	@Column(length=75, nullable = false)
	private String firstName;
	
	@Column(length=75, nullable = false)
	private String lastName;
	
	@Column(length=75, nullable = false)
	private String title; 
			
	@Column(length=255, nullable = true)
	private String companyName;
	
	private String[] addresses;
	
	private String[] phoneNumbers;
}
