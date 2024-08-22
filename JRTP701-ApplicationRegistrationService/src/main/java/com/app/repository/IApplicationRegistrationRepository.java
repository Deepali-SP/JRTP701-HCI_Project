package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CitizenAppRegistrationEntity;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenAppRegistrationEntity, Integer> {

}
