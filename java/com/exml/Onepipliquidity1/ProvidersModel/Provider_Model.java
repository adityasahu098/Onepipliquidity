package com.exml.Onepipliquidity1.ProvidersModel;

import jakarta.persistence.*;

@Entity
@Table(name = "provider_model")
public class Provider_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long providerId;
    
    @Column(name = "Name")
    private String Name;

	@Column(name = "spreads")
    private String spreads;

    @Column(name = "execution_speed")
    private String executionSpeed;

    @Column(name = "liquidity_depth")
    private String liquidityDepth;

    @Column(name = "regulation")
    private String regulation;

    @Column(name = "costs")
    private String costs;

    @Column(name = "risk_management")
    private String riskManagement;

    @Column(name = "customer_support")
    private String customerSupport;

    @Column(name = "reputation")
    private String reputation;

    // Getters and Setters

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getSpreads() {
        return spreads;
    }

    public void setSpreads(String spreads) {
        this.spreads = spreads;
    }

    public String getExecutionSpeed() {
        return executionSpeed;
    }

    public void setExecutionSpeed(String executionSpeed) {
        this.executionSpeed = executionSpeed;
    }

    public String getLiquidityDepth() {
        return liquidityDepth;
    }

    public void setLiquidityDepth(String liquidityDepth) {
        this.liquidityDepth = liquidityDepth;
    }

    public String getRegulation() {
        return regulation;
    }

    public void setRegulation(String regulation) {
        this.regulation = regulation;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getRiskManagement() {
        return riskManagement;
    }

    public void setRiskManagement(String riskManagement) {
        this.riskManagement = riskManagement;
    }

    public String getCustomerSupport() {
        return customerSupport;
    }

    public void setCustomerSupport(String customerSupport) {
        this.customerSupport = customerSupport;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }
    
    public String getName() {
 		return Name;
 	}

 	public void setName(String name) {
 		Name = name;
 	}
    
    
    
}
