package com.skilldistillery.midterm.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.midterm.data.EventDAO;
import com.skilldistillery.midterm.data.MatchDAO;
import com.skilldistillery.midterm.data.UserDAO;
import com.skilldistillery.midterm.entities.Event;
import com.skilldistillery.midterm.entities.EventDTO;
import com.skilldistillery.midterm.entities.Interest;
import com.skilldistillery.midterm.entities.Location;
import com.skilldistillery.midterm.entities.Profile;
import com.skilldistillery.midterm.entities.ProfileDTO;
import com.skilldistillery.midterm.entities.User;

@Controller
public class AdminController {

	@Autowired
	private EventDAO edao;
	@Autowired
	private MatchDAO mdao;
	@Autowired
	private UserDAO udao;

	@RequestMapping(path = "admin.do", method = RequestMethod.GET)
	public ModelAndView adminView() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("WEB-INF/admin.jsp");
		return mv;
	}
	
	//events mapping
	@RequestMapping(path = "getEvents.do", method = RequestMethod.GET)
	public ModelAndView eventIndex() {
		ModelAndView mv = new ModelAndView();
		List<Event> eventList = edao.index();
		mv.addObject("eventList", eventList);
		mv.setViewName("WEB-INF/admin-events.jsp");
		return mv;
	}
	
	@RequestMapping(path = "getEvent.do", method=RequestMethod.GET)
	public ModelAndView getEvent(@RequestParam("id") int eventId) {
		ModelAndView mv = new ModelAndView();
		Event e = edao.getEventById(eventId);
		mv.addObject("event", e);
		mv.setViewName("WEB-INF/eventDetails.jsp");
		return mv;
	}

	@RequestMapping(path = "addEvent.do", method = RequestMethod.GET)
	public ModelAndView addEventGet(Event event) {
		ModelAndView mv = new ModelAndView();
		EventDTO dto = new EventDTO();
		mv.addObject("dto", dto);
		mv.setViewName("WEB-INF/addEvent.jsp");
		return mv;
	}

	@RequestMapping(path = "addEvent.do", method = RequestMethod.POST)
	public ModelAndView addEventPost(EventDTO dto, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		Event event = edao.createEventAndLocation(dto);
		redir.addFlashAttribute("event", event);
		System.out.println(event.getDate());
		mv.setViewName("redirect:eventCreated.do");

		return mv;
	}

	@RequestMapping(path = "eventCreated.do", method = RequestMethod.GET)
	public ModelAndView eventCreated() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/eventDetails.jsp");
		return mv;
	}
	
	@RequestMapping(path = "updateEvent.do", method=RequestMethod.GET)
	public ModelAndView updateEvent(@RequestParam("id") int eventId) {
		ModelAndView mv = new ModelAndView();
		Event e = edao.getEventById(eventId);
		Location l = e.getLocation();
		EventDTO dto = edao.getEventDTOFromEventAndLocation(e, l);
		List<String> interests = Arrays.asList(dto.getInterests());
		mv.addObject("interests", interests);
		mv.addObject("dto", dto);
		mv.addObject("id", e.getId());
		mv.setViewName("WEB-INF/updateEvent.jsp");
		return mv;
	}
	
	@RequestMapping(path = "updateEvent.do", method = RequestMethod.POST)
	public ModelAndView updateEventPost(EventDTO dto, @RequestParam("id") int eventId, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		
		Event event = edao.updateEventAndLocation(dto, eventId);
		redir.addFlashAttribute("event", event);
		mv.setViewName("redirect:eventCreated.do");

		return mv;
	}
	
	@RequestMapping(path = "deleteEvent.do", method = RequestMethod.GET)
	public ModelAndView deleteEvent(@RequestParam("id") int eventId) {
		ModelAndView mv = new ModelAndView();
		edao.delete(eventId);
		List<Event> eventList = edao.index();
		mv.addObject("eventList", eventList);
		mv.setViewName("WEB-INF/admin-events.jsp");
		
		return mv;
	}
	
	//profile mapping
	@RequestMapping(path = "getProfiles.do", method = RequestMethod.GET)
	public ModelAndView profileIndex() {
		ModelAndView mv = new ModelAndView();
		List<Profile> profileList = udao.getAllProfiles();
		mv.addObject("profileList", profileList);
		mv.setViewName("WEB-INF/admin-profiles.jsp");
		return mv;
	}
	
	@RequestMapping(path = "getProfile.do", method=RequestMethod.GET)
	public ModelAndView getProfile(@RequestParam("id") int profileId) {
		ModelAndView mv = new ModelAndView();
		User u = udao.getUserFromProfileID(profileId);
		Profile profile = udao.findProfileByProfileId(profileId);
		ProfileDTO pdto = udao.getProfileDTOfromProfile(profile, u);
		List<String> interests = Arrays.asList(pdto.getInterests());
		mv.addObject("profileId", profile.getId());
		mv.addObject("profile", pdto);
		mv.addObject("interests", interests);
		mv.addObject("profiledto2", new ProfileDTO());
		mv.setViewName("WEB-INF/adminUpdateProfileDetails.jsp"); 
		return mv;
	}
	

	@RequestMapping(path = "adminUpdateProfile.do", method = RequestMethod.GET)
	public ModelAndView updateProfile(@RequestParam("id") int profileId) {
		ModelAndView mv = new ModelAndView();
		User u = udao.getUserFromProfileID(profileId);
		Profile profile = udao.findProfileByProfileId(profileId);
		ProfileDTO pdto = udao.getProfileDTOfromProfile(profile, u);
		List<String> interests = Arrays.asList(pdto.getInterests());
		mv.addObject("profileId", profile.getId());
		mv.addObject("profileUpdate", pdto);
		mv.addObject("interests", interests);
		mv.addObject("profiledto2", new ProfileDTO());
		mv.setViewName("WEB-INF/adminUpdateProfile.jsp"); 
		return mv;
	}

	@RequestMapping(path = "adminUpdateProfileDetails.do", method = RequestMethod.POST)
	public ModelAndView updateProfileDetails(@RequestParam("id") int profileId, ProfileDTO profiledto) {
		ModelAndView mv = new ModelAndView();
		User u = udao.getUserFromProfileID(profileId);
		Profile profile = udao.findProfileByProfileId(profileId);
		Profile p = udao.getProfilefromProfileDTO(profiledto, u);
		mv.addObject("profileId", profile.getId());
		mv.addObject("profileUpdated", profiledto);
		mv.setViewName("WEB-INF/updatedProfileDetails.jsp");
		return mv;
	}
	
	@RequestMapping(path = "addProfile.do", method = RequestMethod.GET)
	public ModelAndView addProfileGet(Profile profile) {
		ModelAndView mv = new ModelAndView();
		Profile p = new Profile();
		Location l = new Location();
		mv.addObject("profile", p);
		mv.addObject("location", l);
		mv.setViewName("WEB-INF/addprofile.jsp");
		return mv;
	}
	
	@RequestMapping(path = "addProfile.do", method = RequestMethod.POST)
	public ModelAndView addProfilePost(Profile profile, Location location, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView();
		udao.createProfileAndLocation(profile, location);
		redir.addFlashAttribute("profile", profile);
		mv.setViewName("redirect:profileCreated.do");
		
		return mv;
	}
	
	@RequestMapping(path = "profileCreated.do", method = RequestMethod.GET)
	public ModelAndView profileCreated() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/profileDetails.jsp");
		return mv;
	}
	
	
	
}
