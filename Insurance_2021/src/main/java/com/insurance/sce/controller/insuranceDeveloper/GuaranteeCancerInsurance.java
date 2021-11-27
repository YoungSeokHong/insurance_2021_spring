package com.insurance.sce.controller.insuranceDeveloper;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurance.sce.global.Constants;
import com.insurance.sce.global.Constants.eFamilyMedicalRelationship;
import com.insurance.sce.model.insurance.Insurance;
import com.insurance.sce.service.InsuranceDeveloperService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/")
public class GuaranteeCancerInsurance {
	private Insurance insurance;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value="guaranteeCancerInsurance", method=RequestMethod.GET)
	public String responseGuaranteeCancerInsurance(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		this.insurance = (Insurance) session.getAttribute("ratedInsurance");
		int i = 1;
		for(String e: Constants.cancerGuarantee) {
			model.addAttribute("cancerGuarantee"+i, e);
			i++;
		}
		return "insuranceDeveloper/guaranteeCancerInsurance";
	}
	@RequestMapping(value="checkInsurance", method=RequestMethod.GET)
	public String responseCheck(Locale locale, Model model, HttpServletRequest request) throws Exception{
		String[] familyMeical = {"thyroidRate", "testicularRate", "ovarianRate", "esophagealRate", "lungRate"};
		String[] familyRelationship = {"oneRelRate", "twoRelRate", "threeRelRate", "fourRelRate"};
		double[] familyMeicalRate = new double[familyMeical.length];
		double[] familyRelationshipRate = new double[familyRelationship.length];
		for(int i = 0; i < familyMeical.length; i++) {
			familyMeicalRate[i] = Double.parseDouble(request.getParameter(familyMeical[i]));
		}
		for(int i = 0; i < familyRelationship.length; i++) {
			familyRelationshipRate[i] = Double.parseDouble(request.getParameter(familyRelationship[i]));
		}
		
		InsuranceDeveloperService idService = new InsuranceDeveloperService();
		this.insurance = idService.setCancerRate(this.insurance, familyMeicalRate, familyRelationshipRate);
		HttpSession session = request.getSession(true);
		session.setAttribute("ratedInsurance", this.insurance);
		String nextViewUrl = "";
		switch(this.insurance.getType()) {
		case driverInsurance:
			nextViewUrl = "redirect:/guaranteeDriverInsurance";
			break;
		case fireInsurance:
			nextViewUrl = "redirect:/guaranteeFireInsurance";
			break;
		case cancerInsurance:
			nextViewUrl = "redirect:/guaranteeCancerInsurance";
			break;
		case actualCostInsurance:
			nextViewUrl = "redirect:/guaranteeActualCostInsurance";
			break;
		case tripInsurance:
			nextViewUrl = "redirect:/guaranteeTripInsurance";
			break;
		case dentalInsurance:
			nextViewUrl = "redirect:/guaranteeDentalInsurance";
			break;
		default:
			nextViewUrl = "redirect:/developInsurance";
			break; 
		}
		return nextViewUrl;
	}

}
