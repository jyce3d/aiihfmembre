package be.sdlg.webapps.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

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

import be.sdlg.webapps.model.Email;
import be.sdlg.webapps.model.Membre;
import be.sdlg.webapps.services.AiihService;

@Controller
@Transactional
@RequestMapping("email.htm")
public class EmailController {
	@Autowired
	private AiihService aiihSvc;
	
	@ModelAttribute("yesNoItems")
	public Map<String, String> populateYesNo() {
		Map<String, String> yesNo = new HashMap<String, String>();
    	yesNo.put(new Long(Membre.NO).toString(),"Non");
    	yesNo.put(new Long(Membre.YES).toString(),"Oui");
        return yesNo;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getMember(@RequestParam(value="member-id", required=true) Long memberId, 
							@RequestParam(value="id", required=false) Long id, 
							Model model) {
		Membre member = aiihSvc.getMember(memberId);
		Email email = null;
		if (id==null) {
			email = new Email();
		}
		else email = aiihSvc.getEmail(id);
	
		model.addAttribute("email", email);
		return "email";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String updateMember(@RequestParam(value="member-id", required=true) Long memberId, Email email, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) 
			return "email";

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		aiihSvc.saveEmail(memberId, email.getReasonForChange(), username, email);
		return "redirect:home.htm";
		
	}

}
