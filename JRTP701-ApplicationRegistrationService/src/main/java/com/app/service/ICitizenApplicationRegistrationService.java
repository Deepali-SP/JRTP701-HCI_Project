package com.app.service;

import com.app.binding.CitizenAppRegistrationInputs;
import com.app.exception.InvalidSSNException;

public interface ICitizenApplicationRegistrationService {

	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException;
}
