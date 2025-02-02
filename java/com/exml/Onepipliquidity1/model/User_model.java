package com.exml.Onepipliquidity1.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_model")

public class User_model {
	
	
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@JsonProperty("Onepipliquidity_modelId")
private int onepipliquidityModelId;

@Column(name = "first_name", nullable = false)
private String firstName;




@Column(name = "last_name", nullable = false)
private String lastName;

@Column(name = "birthday", nullable = false)
private LocalDate birthday;

@Column(name = "email", nullable = false, unique = true)
private String email;


@Enumerated(EnumType.STRING)
@Column(name = "country", nullable = false)
private Country country;

@Column(name = "reset_token")
private String resetToken;


@Column(name = "password", nullable = false)
private String password;

// Enum for countries
public enum Country {
    UNITED_STATES,
    CANADA,
    UNITED_KINGDOM,
    AUSTRALIA,
    INDIA,
    GERMANY,
    FRANCE,
    CHINA,
    JAPAN,
    BRAZIL,
    RUSSIA,
    SOUTH_AFRICA
}


// Getters and Setters
public int getOnepipliquidityModelId() {
return onepipliquidityModelId;
}

public void setOnepipliquidityModelId(int onepipliquidityModelId) {
this.onepipliquidityModelId = onepipliquidityModelId;
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

public LocalDate getBirthday() {
return birthday;
}

public void setBirthday(LocalDate birthday) {
this.birthday = birthday;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public Country getCountry() {
return country;
}

public void setCountry(Country country) {
this.country = country;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}



public String getResetToken() {
    return resetToken;
}

public void setResetToken(String resetToken) {
    this.resetToken = resetToken;
}

public boolean isPresent() {
	// TODO Auto-generated method stub
	return false;
}  


}
