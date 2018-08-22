package be.sdlg.webapps.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import be.sdlg.webapps.model.Address;
import be.sdlg.webapps.model.AddressAudit;
import be.sdlg.webapps.model.Email;
import be.sdlg.webapps.model.EmailAudit;
import be.sdlg.webapps.model.MemberAudit;
import be.sdlg.webapps.model.Membre;
import be.sdlg.webapps.services.AiihService;

@Controller
@Transactional
public class HomeController {
	@Autowired
	private AiihService aiihSvc;
	
	@RequestMapping(value={"/", "home.htm"}, method=RequestMethod.GET)
	public String home(Model model) {
	
		
		Set<Membre> memberList = aiihSvc.retrieveMembers();
		model.addAttribute("memberList", memberList);
		return "home";
	}
	@RequestMapping(value="reports.htm", method=RequestMethod.GET)
	public String reports(Model model) {
		return "reports";
	}
	@RequestMapping(value="delMembre.htm", method=RequestMethod.GET)
	public String delMembre(@RequestParam(value="id", required=true) Long memberId, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		aiihSvc.delMember(memberId, username);
		
		return home(model);
	}
	@RequestMapping(value="delEmail.htm", method=RequestMethod.GET)
	public String delEmail(@RequestParam(value="member-id", required=true) Long memberId,
			@RequestParam(value="id", required=true) Long emailId, Model model) {
		if (memberId !=null && emailId!=null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			aiihSvc.delEmail(memberId, emailId, username);
		}
		return home(model);
	}
	@RequestMapping(value="audit.htm", method=RequestMethod.GET)
	public String audit(@RequestParam(value="id", required=true) Long memberId, Model model) {
		
		if (memberId !=null) {
			Membre m = aiihSvc.getMember(memberId);
			model.addAttribute("m", m);
			// Récupérer l'audit
			List<MemberAudit> maList = aiihSvc.retrieveMemberAudit(memberId);
			model.addAttribute("maList", maList);
			HashMap<String, String> addrHash = new HashMap<String, String>();
			Address addr = aiihSvc.getCurrentAddress(m);
			addrHash.put("number", addr.getNumber());
			addrHash.put("street", addr.getStreet());
			addrHash.put("zip", addr.getPostalCode().getZipCode().toString());
			addrHash.put("city", addr.getPostalCode().getCityName());
			addrHash.put("country", addr.getPostalCode().getCountry().getShortCode());
			
			model.addAttribute("addr", addrHash);
			// Récuperer l'adresse
			List<AddressAudit> aaList = aiihSvc.retrieveAddressAudit(memberId);
			model.addAttribute("aaList", aaList);
			
			List<Email> emailList = aiihSvc.retrieveEmail(memberId);
			model.addAttribute("emailList", emailList);
			
		}
		return "audit-membre";
	}	
	@RequestMapping(value="audit_email.htm", method=RequestMethod.GET)
	public String auditEmail(@RequestParam(value="id", required=true) Long emailId, Model model) {
		if (emailId !=null) {
			Email em = aiihSvc.getEmail(emailId);
			List<EmailAudit> emaList = aiihSvc.retrieveEmailAudit(emailId);
			model.addAttribute("emaList", emaList);
		}
		return "audit-email";
	}	
	@RequestMapping(value="logout.htm", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";
	}
	
}
