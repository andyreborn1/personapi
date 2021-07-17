package com.nemowave.personapi.repository;

import com.nemowave.personapi.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
}
