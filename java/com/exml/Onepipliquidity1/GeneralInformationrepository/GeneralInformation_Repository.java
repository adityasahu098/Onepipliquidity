package com.exml.Onepipliquidity1.GeneralInformationrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exml.Onepipliquidity1.GeneralInformationModel.GeneralInformation_model;

@Repository
public interface GeneralInformation_Repository extends JpaRepository<GeneralInformation_model, Long> {

}
