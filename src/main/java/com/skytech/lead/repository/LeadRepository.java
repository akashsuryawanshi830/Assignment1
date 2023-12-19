package com.skytech.lead.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.skytech.lead.entity.Lead;


@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    boolean existsByLeadId(Long leadId);

    boolean existsByEmail(String email);

	List<Lead> findByMobileNumber(String mobileNumber);
}


