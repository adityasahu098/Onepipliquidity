package com.exml.Onepipliquidity1.providerscontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exml.Onepipliquidity1.ProviderRepository.Providerrepository;
import com.exml.Onepipliquidity1.ProvidersModel.Provider_Model;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ProvidersController {

	private static final Logger logger = LoggerFactory.getLogger(ProvidersController.class);
	
    @Autowired
    private Providerrepository providerRepository;
    
    
    
    @PostMapping("/addProvider")  // This will map to /api/addProvider
	 public ResponseEntity<?> addProvider(@RequestBody Provider_Model newprovider) {
	     try {
	    	 Provider_Model savedprovider = providerRepository.save(newprovider);
	         logger.info("Successfully added provider: {}", savedprovider);
	         
	         // Custom Response Object
	         Map<String, Object> response = new HashMap<>();
	         response.put("provider_id", savedprovider.getProviderId()); // Ensure the getter exists
	         response.put("statusCode", HttpStatus.CREATED.value());
	         response.put("name", savedprovider.getName()); // Assuming you have a getCategoryName() method
	        

	         return new ResponseEntity<>(response, HttpStatus.CREATED);
	     } catch (Exception ex) {
	         logger.error("Error occurred while adding provider: ", ex);
	         
	         // Custom Error Response
	         Map<String, Object> errorResponse = new HashMap<>();
	         errorResponse.put("provider_id", null);
	         errorResponse.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
	         errorResponse.put("name", null);
	        

	         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
    
    
    
    
    @GetMapping("/getAllProviders")  // This will map to /api/getAllProvider
	 public ResponseEntity<?> getAllProviders() {
	     try {
	         List<Provider_Model> Provider = providerRepository.findAll();  // This retrieves all Provider from the database
	       
	         // Custom Response Object
	         Map<String, Object> response = new HashMap<>();
	         response.put("Provider", Provider); // List of all Provider
	         response.put("statusCode", HttpStatus.OK.value());
	         return new ResponseEntity<>(response, HttpStatus.OK);
	     } catch (Exception ex) {
	         logger.error("Error occurred while fetching Provider: ", ex);
	         // Custom Error Response
	         Map<String, Object> errorResponse = new HashMap<>();
	         errorResponse.put("Provider", null);
	         errorResponse.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
    
    
    
    
    
    @GetMapping("/getProvider/{id}")  // This will map to /api/getProvider/{id}
    public ResponseEntity<?> getProviderById(@PathVariable Long id) {
        try {
            Optional<Provider_Model> provider = providerRepository.findById(id);

            if (provider.isPresent()) {
                // Custom Response Object
                Map<String, Object> response = new HashMap<>();
                response.put("provider", provider.get());
                response.put("statusCode", HttpStatus.OK.value());

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Provider not found
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "Provider not found");
                errorResponse.put("statusCode", HttpStatus.NOT_FOUND.value());

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error("Error occurred while fetching provider by ID: ", ex);

            // Custom Error Response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    @DeleteMapping("/deleteProvider/{id}")  // This will map to /api/deleteProvider/{id}
    public ResponseEntity<?> deleteProviderById(@PathVariable Long id) {
        try {
            Optional<Provider_Model> provider = providerRepository.findById(id);

            if (provider.isPresent()) {
                providerRepository.deleteById(id);

                // Custom Response Object
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Provider deleted successfully");
                response.put("statusCode", HttpStatus.OK.value());

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Provider not found
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "Provider not found");
                errorResponse.put("statusCode", HttpStatus.NOT_FOUND.value());

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error("Error occurred while deleting provider: ", ex);

            // Custom Error Response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    @PutMapping("/updateProvider/{id}")  // This will map to /api/updateProvider/{id}
    public ResponseEntity<?> updateProvider(@PathVariable Long id, @RequestBody Provider_Model updatedProvider) {
        try {
            Optional<Provider_Model> existingProvider = providerRepository.findById(id);

            if (existingProvider.isPresent()) {
                Provider_Model provider = existingProvider.get();

                // Update the fields
                provider.setName(updatedProvider.getName());
                provider.setSpreads(updatedProvider.getSpreads());
                provider.setExecutionSpeed(updatedProvider.getExecutionSpeed());
                provider.setLiquidityDepth(updatedProvider.getLiquidityDepth());
                provider.setRegulation(updatedProvider.getRegulation());
                provider.setCosts(updatedProvider.getCosts());
                provider.setRiskManagement(updatedProvider.getRiskManagement());
                provider.setCustomerSupport(updatedProvider.getCustomerSupport());
                provider.setReputation(updatedProvider.getReputation());

                Provider_Model savedProvider = providerRepository.save(provider);

                // Custom Response Object
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Provider updated successfully");
                response.put("statusCode", HttpStatus.OK.value());
                response.put("updatedProvider", savedProvider);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                // Provider not found
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "Provider not found");
                errorResponse.put("statusCode", HttpStatus.NOT_FOUND.value());

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error("Error occurred while updating provider: ", ex);

            // Custom Error Response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Internal Server Error");
            errorResponse.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}
