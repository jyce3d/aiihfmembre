package be.sdlg.webapps.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.sdlg.webapps.model.Address;
import be.sdlg.webapps.model.Country;
import be.sdlg.webapps.model.Membre;
import be.sdlg.webapps.model.PostalCode;
import be.sdlg.webapps.services.AiihService;

@Controller
@Transactional
@RequestMapping("member.htm")
public class MemberController {
	@Autowired
	private AiihService aiihSvc;
	
	@ModelAttribute("yesNoItems")
	public Map<String, String> populateYesNo() {
		Map<String, String> yesNo = new HashMap<String, String>();
    	yesNo.put(new Long(Membre.NO).toString(),"Non");
    	yesNo.put(new Long(Membre.YES).toString(),"Oui");
        return yesNo;
	}
	
	@ModelAttribute("specialityItems")
	public Map<String, String> populateSpeciality() {
		Map<String, String> specialityItems = new HashMap<String, String>();
    	specialityItems.put(new Long(Membre.AGRO).toString(),"AGRONOMIE");
    	specialityItems.put(new Long(Membre.BIOCH).toString(),"BIOCHIMIE");
    	specialityItems.put(new Long(Membre.CHIM).toString(),"CHIMIE");
    	specialityItems.put(new Long(Membre.CONS).toString(),"CONSTRUCTION");
    	specialityItems.put(new Long(Membre.ELEC).toString(),"ELECTRICITE");
    	specialityItems.put(new Long(Membre.ELEL).toString(),"ELECTRONIQUE");
    	specialityItems.put(new Long(Membre.ELME).toString(),"ELECTROTECHNIQUE");
    	specialityItems.put(new Long(Membre.MECA).toString(),"MECANIQUE");

        return specialityItems;
	}
	@ModelAttribute("statusAIIHItems")
	public Map<String, String> populateStatusAIIH() {
		Map<String, String> statusAIIHItems = new HashMap<String, String>();
		statusAIIHItems.put(new Long(Membre.AIIH_ADMINISTRATEUR).toString(),"Administrateur");
		statusAIIHItems.put(new Long(Membre.AIIH_MEMBRE).toString(),"Membre");
		statusAIIHItems.put(new Long(Membre.AIIH_PRESIDENT).toString(),"President");
		statusAIIHItems.put(new Long(Membre.AIIH_SECRETAIRE).toString(),"Secrétaire");
		statusAIIHItems.put(new Long(Membre.AIIH_TRESORIER).toString(),"Trésorier");
		statusAIIHItems.put(new Long(Membre.AIIH_UNSUBSCRIBED).toString(),"Désinscrit");
		statusAIIHItems.put(new Long(Membre.AIIH_VP).toString(),"Vice Président");
		statusAIIHItems.put(new Long(Membre.AIIH_EXCOMMUNIE).toString(),"eXcommunié");

        return statusAIIHItems;
	}
	@ModelAttribute("implantationItems")
	public Map<String, String> populateImplantation() {
		Map<String, String> implantationItems = new HashMap<String, String>();
		implantationItems.put(new Long(Membre.ATH).toString(),"Ath");
		implantationItems.put(new Long(Membre.CHARLEROI).toString(),"Charleroi");
		implantationItems.put(new Long(Membre.TOURNAI).toString(),"Tournai");
		return implantationItems;
	}
	@ModelAttribute("countryItems")
	public Map<String, String> initCountry() {
		Map<String, String> countryItems = new HashMap<String, String>();
		Set<Country> countries = aiihSvc.retrieveCountryList();
		for (Country cnty : countries) 
			countryItems.put(cnty.getId().toString(), cnty.getShortCode());
		
		return countryItems;
	}

/*	@ModelAttribute("postalCodeItems")
	public Map<String, String> initPostalCodes() {
		Map<String, String> postalCodeItems = new HashMap<String, String>();
		Set<PostalCode> postalCodes = aiihSvc.retrievePostalCodeList();
		List<PostalCode> sortPC = new ArrayList<PostalCode>(postalCodes);
		Collections.sort (sortPC);*/
/*		for (PostalCode pc : sortPC)
			System.out.println(pc.getZipCode();*/
/*		for (PostalCode pc : sortPC) {
			postalCodeItems.put(pc.getId().toString(), pc.getZipCode()+ " | "+pc.getCityName());
		}
		return postalCodeItems;
	}*/
	
	@RequestMapping(method=RequestMethod.GET)
	public String getMember(@RequestParam(value="id", required=false) Long id, Model model) {
		Membre member;
		if (id==null) {
			member = new Membre();
		}
		else member = aiihSvc.getMember(id);
		Set<PostalCode> postalCodes = aiihSvc.retrievePostalCodeList();
		List<PostalCode> sortPC = new ArrayList<PostalCode>(postalCodes);
		Collections.sort (sortPC);
		model.addAttribute("sortPC", sortPC);
		model.addAttribute("member", member);
		Address ad = aiihSvc.getCurrentAddress(member);
		if (ad!=null)
			model.addAttribute("pc", ad.getPostalCode());
		return "member";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String updateMember(Membre member, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
			return "member";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		aiihSvc.saveMember(member, member.getReasonForChange(), username );
		return "redirect:home.htm";
		
	}
}
