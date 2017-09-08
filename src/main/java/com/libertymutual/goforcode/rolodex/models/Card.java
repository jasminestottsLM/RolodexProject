package com.libertymutual.goforcode.rolodex.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
public class Card {

    public Card() {
    }

    public Card(Long id, String firstName, String lastName, String title) {
        this.id = id;
        this.firstName = firstName;
        this.title = title;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("first_name")
    @Column(length = 75, nullable = false)
    private String firstName;

    @JsonProperty("last_name")
    @Column(length = 75, nullable = false)
    private String lastName;

    @JsonProperty("person_title")
    @Column(length = 75, nullable = false)
    private String title;

    @JsonProperty("person_co")
    @Column(length = 255, nullable = true)
    private String companyName;

    @JsonProperty("picture")
    @Column(length = 255, nullable = true)
    private String picture;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public void addAddress(Address address) {
        if (addresses == null) {
            addresses = new ArrayList<Address>();
        }
        addresses.add(address);
    }

    public void addPhoneNumber(PhoneNumber phone) {
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<PhoneNumber>();
        }
        phoneNumbers.add(phone);
    }

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
