package com.app.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ssa-web-api")
public class SSAWebOperationsRestController {

	@GetMapping("/find/{ssn}")
	public ResponseEntity<String> getStateBySSN(@PathVariable Integer ssn){
		if(String.valueOf(ssn).length()!=9)
		 return new ResponseEntity<String>("ssn must be 9 digits number", HttpStatus.BAD_REQUEST);
		
		//get state name
		int stateCode=ssn%100;
		String stateName=null;
		
		if(stateCode==01)
		 stateName="Washington DC";
		else if(stateCode==02)
			stateName="Ohio";
		else if(stateCode==03)
			stateName="Texas";
		else if(stateCode==04)
			stateName="California";
		else if(stateCode==05)
			stateName="Florida";
		
		else
			stateName="Invalid SSN";
		return new ResponseEntity<String>(stateName, HttpStatus.OK);
	}
}
