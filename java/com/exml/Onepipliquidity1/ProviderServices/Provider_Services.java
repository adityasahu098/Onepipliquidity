package com.exml.Onepipliquidity1.ProviderServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exml.Onepipliquidity1.ProviderRepository.Providerrepository;
import com.exml.Onepipliquidity1.ProvidersModel.Provider_Model;


@Service
public class Provider_Services {

    @Autowired
    private Providerrepository providerRepository;

  
    
    
    // Method to add a new Provider
    public Provider_Model addProvider(Provider_Model newprovider) {
        try {
            return providerRepository.save(newprovider);
        } catch (Exception ex) {
            throw new RuntimeException("Error while adding Provider", ex);
        }
    }

    // Get all Providers
    public List<Provider_Model> getAllProviders() {
        return providerRepository.findAll();
    }

    // Get Provider by ID
    public Optional<Provider_Model> getProviderById(Long id) {
        return providerRepository.findById(id);
    }

  
    
    public Provider_Model updateProvider(Long id, Provider_Model providerDetails) {
        Optional<Provider_Model> existingProviderOpt = providerRepository.findById(id);

        if (existingProviderOpt.isPresent()) {
            Provider_Model existingProvider = existingProviderOpt.get();

            // Update the fields
            existingProvider.setName(providerDetails.getName());
            existingProvider.setSpreads(providerDetails.getSpreads());
            existingProvider.setExecutionSpeed(providerDetails.getExecutionSpeed());
            existingProvider.setLiquidityDepth(providerDetails.getLiquidityDepth());
            existingProvider.setRegulation(providerDetails.getRegulation());
            existingProvider.setCosts(providerDetails.getCosts());
            existingProvider.setRiskManagement(providerDetails.getRiskManagement());
            existingProvider.setCustomerSupport(providerDetails.getCustomerSupport());
            existingProvider.setReputation(providerDetails.getReputation());

            return providerRepository.save(existingProvider);
        } else {
            return null; // Provider not found
        }
    }
    
    
    

    // Delete Provider by ID
    public boolean deleteProvider(Long id) {
        Optional<Provider_Model> provider = providerRepository.findById(id);
        if (provider.isPresent()) {
            providerRepository.deleteById(id);
            return true;
        }
        return false;  // Provider not found
    }
}
