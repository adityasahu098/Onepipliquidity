package com.exml.Onepipliquidity1.GeneralInformationController;



import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exml.Onepipliquidity1.GeneralInformationModel.GeneralInformation_model;
import com.exml.Onepipliquidity1.GeneralInformationrepository.GeneralInformation_Repository;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GeneralInformationcontroller {

	
	
	private static final Logger logger = LoggerFactory.getLogger(GeneralInformationcontroller.class);
	
	
	  @Autowired
	    private GeneralInformation_Repository generalInformation_Repository;
	 
    @PostMapping("/addInformation")  // This will map to /api/addInformation
	 public ResponseEntity<?> GeneralInformation_model(@RequestBody GeneralInformation_model newInformation) {
	     try {
	    	 GeneralInformation_model savedInformation = generalInformation_Repository.save(newInformation);
	         logger.info("Successfully added Information: {}", savedInformation);
	         
	         // Custom Response Object
	         Map<String, Object> response = new HashMap<>();
	         response.put("provider_id", savedInformation.getId()); // Ensure the getter exists
	         response.put("statusCode", HttpStatus.CREATED.value());
	       

	         return new ResponseEntity<>(response, HttpStatus.CREATED);
	     } catch (Exception ex) {
	         logger.error("Error occurred while adding Information: ", ex);
	         
	         // Custom Error Response
	         Map<String, Object> errorResponse = new HashMap<>();
	         errorResponse.put("provider_id", null);
	         errorResponse.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
	      
	        

	         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
}
