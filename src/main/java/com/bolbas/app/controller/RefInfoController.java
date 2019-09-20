package com.bolbas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bolbas.app.dao.RefInfoRepo;
import com.bolbas.app.entity.City;
import com.bolbas.app.entity.ReferenceInformation;

@RestController
public class RefInfoController {

	private static final String NO_INFO = "No information";
	private static final String ALREADY_EXISTS = "The city and information about it has already been recorded";
	private static final String NOT_EXISTS = "The city and information about it does not exist";

	@Autowired
	private RefInfoRepo refInfoRepo;

	/**
	 * This method gets info about the current 'city'
	 * 
	 * @param city
	 * @return String
	 */
	@RequestMapping(value = "/getDisc", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String getDisc(String city) {
		String answer = null;
		ReferenceInformation refInformation = refInfoRepo.findByCity(city);
		if (refInformation == null) {
			answer = NO_INFO;
		} else {
			answer = refInformation.getDescription();
		}
		return answer;
	}

	/**
	 * This method adds the 'city' and the info about it
	 * 
	 * @param refInfo
	 * @return String
	 */
	@RequestMapping(value = "/addCity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public String addCity(@RequestBody ReferenceInformation refInfo) {
		String answer = null;
		if (refInfo.getCity() == null) {
			return NO_INFO;
		}
		ReferenceInformation referenceInformation = refInfoRepo.findByCity(refInfo.getCity());
		if (referenceInformation == null) {
			answer = saveToDB(refInfo, "Add");
		} else {
			answer = ALREADY_EXISTS;
		}
		return answer;
	}

	/**
	 * This method removes full info about the 'city'
	 * 
	 * @param city
	 * @return String
	 */
	@RequestMapping(value = "/removeCity", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public String removeCity(@RequestBody City city) {
		long result = refInfoRepo.deleteByCity(city.getCity());
		return "remove " + result;
	}

	/**
	 * This method changes info about the 'city'
	 * 
	 * @param refInfo
	 * @return String
	 */
	@RequestMapping(value = "/updateDesc", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public String setDisc(@RequestBody ReferenceInformation refInfo) {
		String answer = null;
		if (refInfo.getCity() == null) {
			return NO_INFO;
		}
		ReferenceInformation referenceInformation = refInfoRepo.findByCity(refInfo.getCity());

		if (referenceInformation == null) {
			answer = NOT_EXISTS;
		} else {
			refInfo.setId(referenceInformation.getId());
			answer = saveToDB(refInfo, "Update");
		}
		return answer;
	}

	/**
	 * This method deletes the info (description) about this 'city'
	 * 
	 * @param city
	 * @return String
	 */
	@RequestMapping(value = "/removeDesc", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
	public String removeDisc(@RequestBody City city) {
		String answer = null;
		if (city.getCity() == null) {
			return NO_INFO;
		}
		ReferenceInformation referenceInformation = refInfoRepo.findByCity(city.getCity());
		if (referenceInformation == null) {
			answer = NOT_EXISTS;
		} else {
			referenceInformation.setDescription(null);
			answer = saveToDB(referenceInformation, "Remove desc");
		}
		return answer;
	}

	/**
	 * This method stores information about the city and its description in the
	 * database. If the description is missing, then save - "No information"
	 * 
	 * @param refInfo
	 * @return String
	 */
	private String saveToDB(ReferenceInformation refInfo, String mode) {
		if (refInfo.getDescription() == null || refInfo.getDescription().isEmpty()) {
			refInfo.setDescription(NO_INFO);
			refInfoRepo.save(refInfo);
		} else {
			refInfoRepo.save(refInfo);
		}
		return mode + " - " + refInfo.toString();
	}

	public ReferenceInformation getFromDB(String message) {
		return refInfoRepo.findByCity(message);
	}

}
