package com.insurance.sce.controller.customer;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurance.sce.model.customer.Customer;
import com.insurance.sce.service.InsuranceService;
import com.insurance.sce.service.InsuranceServiceImpl;

@Controller
@RequestMapping(value = "/")
public class SignUpInsurant {
	
	private static final Logger logger = LoggerFactory.getLogger(SelectTripInsuranceController.class);
	
	@Autowired
	InsuranceServiceImpl insuranceService;
	
	@RequestMapping(value="signUpInsurant", method=RequestMethod.GET)
	public String response5(Locale locale, Model model, HttpServletRequest request, String insuranceId) {
		HttpSession session = request.getSession(true);
		Customer customer = (Customer) session.getAttribute("loginCustomer");
		model.addAttribute("insuranceId", insuranceId);
		return "customer/signUpInsurant";
	}
}