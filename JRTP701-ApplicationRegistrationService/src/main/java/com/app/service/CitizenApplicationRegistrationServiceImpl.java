package com.app.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.binding.CitizenAppRegistrationInputs;
import com.app.entity.CitizenAppRegistrationEntity;
import com.app.exception.InvalidSSNException;
import com.app.repository.IApplicationRegistrationRepository;

import reactor.core.publisher.Mono;


@Service
public class CitizenApplicationRegistrationServiceImpl implements ICitizenApplicationRegistrationService {

	@Autowired
	private IApplicationRegistrationRepository citizenRepo;
	//@Autowired
	//private RestTemplate template;
	@Value("${ar.ssa-web.url}")
	private String endPointUrl;
	@Value("${ar.state}")
	private String targetState;
	
	@Autowired
	private WebClient client;
	
	@Override
	public Integer registerCitizenApplication(CitizenAppRegistrationInputs inputs) throws InvalidSSNException{

/*		//perform webService call to check whether SSN valid or not and to get the state name
		ResponseEntity<String> response = template.exchange(endPointUrl, HttpMethod.GET, null, String.class, inputs.getSsn());
		//get state name
		String stateName=response.getBody();
		//register citizen if he belongs to california state(CA)
		if(stateName.equalsIgnoreCase(targetState)) {
			//prepare th Entity object
			CitizenAppRegistrationEntity entity=new CitizenAppRegistrationEntity();
			entity.setStateName(stateName);
			BeanUtils.copyProperties(inputs, entity);
			//save the object
			int appId = citizenRepo.save(entity).getAppId();
			return appId;
		}
		return 0;
	}*/
		
		//perform webService call to check whether SSN valid or not and to get the state name(using web client)
	//get state name	
//	String stateName=client.get().uri(endPointUrl, inputs.getSsn()).retrieve().bodyToMono(String.class).block();
		Mono<String> response=client
				               .get()
				               .uri(endPointUrl, inputs.getSsn())
				               .retrieve()
				               .onStatus(HttpStatus.BAD_REQUEST::equals, res->res.bodyToMono(String.class).map(ex->new InvalidSSNException("invalid ssn")))
				               .bodyToMono(String.class);
		String stateName=response.block();
	//register citizen if he belongs to California state(CA)
	if(stateName.equalsIgnoreCase(targetState)) {
		//prepare entity object
		CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
		entity.setStateName(stateName);
		BeanUtils.copyProperties(inputs, entity);
		//save object
		int appId=citizenRepo.save(entity).getAppId();
		return appId;
	}//if
	throw new InvalidSSNException("invalid ssn exception");
	}

}




















