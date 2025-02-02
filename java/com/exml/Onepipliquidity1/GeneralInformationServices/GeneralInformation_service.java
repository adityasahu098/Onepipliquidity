package com.exml.Onepipliquidity1.GeneralInformationServices;

import org.springframework.beans.factory.annotation.Autowired;

import com.exml.Onepipliquidity1.GeneralInformationModel.GeneralInformation_model;
import com.exml.Onepipliquidity1.GeneralInformationrepository.GeneralInformation_Repository;


public class GeneralInformation_service {

	
	 @Autowired
	    private GeneralInformation_Repository generalInformation_Repository;
	
	 // Method to add a new addInformation
    public GeneralInformation_model GeneralInformation_model (GeneralInformation_model newInformation) {
        try {
            return generalInformation_Repository.save(newInformation);
        } catch (Exception ex) {
            throw new RuntimeException("Error while adding Information", ex);
        }
    }
	
	
}
