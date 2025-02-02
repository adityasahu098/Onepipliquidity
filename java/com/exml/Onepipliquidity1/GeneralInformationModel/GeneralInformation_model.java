package com.exml.Onepipliquidity1.GeneralInformationModel;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "general_information")
public class GeneralInformation_model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "First Name cannot be null")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;

    @Column(name = "language")
    private String language = "English"; // Default to English

    @NotNull(message = "Company Name cannot be null")
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "website")
    private String website;

    @NotNull(message = "Phone Number cannot be null")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "provider_details")
    private String providerDetails;

    @NotNull(message = "Selected Provider cannot be null")
    @Column(name = "selected_provider")
    private String selectedProvider;

    @Column(name = "requirements")
    private String requirements;

    // Getters and Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProviderDetails() {
        return providerDetails;
    }

    public void setProviderDetails(String providerDetails) {
        this.providerDetails = providerDetails;
    }

    public String getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(String selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
