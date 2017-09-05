package com.liberymutual.goforcode.rolodex.models;

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
	
}
