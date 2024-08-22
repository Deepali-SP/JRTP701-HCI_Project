package com.app.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.binding.CitizenAppRegistrationInputs;
import com.app.service.ICitizenApplicationRegistrationService;

@RestController
@RequestMapping("/CitizenAR-api")
public class CitizenApplicationRegistrationService {

	@Autowired
	private ICitizenApplicationRegistrationService registrationService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveCitizenApplication(@RequestBody CitizenAppRegistrationInputs inputs) throws Exception{
		//use service
		int appId=registrationService.registerCitizenApplication(inputs);
		return new ResponseEntity<String>("Citizen Application register with the Id::"+appId, HttpStatus.CREATED);
		
	}//method
	
}//class
