package com.skilldistillery.midterm.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.midterm.data.EventDAO;
import com.skilldistillery.midterm.data.LoginDAO;
import com.skilldistillery.midterm.data.MatchDAO;
import com.skilldistillery.midterm.data.UserDAO;
import com.skilldistillery.midterm.entities.Profile;
import com.skilldistillery.midterm.entities.ProfileDTO;
import com.skilldistillery.midterm.entities.User;
import com.skilldistillery.midterm.security.PasswordMD5Hash;

@Controller
public class LoginController {
	
	@Autowired
	private EventDAO edao;
	@Autowired
	private MatchDAO mdao;
	@Autowired
	private UserDAO udao;
	@Autowired
	private LoginDAO ldao;
	@Autowired
	private PasswordMD5Hash md5;
	
	@RequestMapping(path = "login.do", method = RequestMethod.GET)
	public String displayLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "WEB-INF/login.jsp";
	}

	@RequestMapping(path ="login.do", method = RequestMethod.POST) 
	public String loginAttempt (User user, HttpSession http, Errors errors) {
		String pHash = md5.hashPassword(user.getPassword());
		User u = ldao.getUserByUserNameAndPassword(user.getUsername(), pHash);
		Profile p = null;
		if(u != null) {
			p = udao.findProfileById(u.getId());
		}
		errors.rejectValue("username", "error.user", "Not a valid login.");
		if (u != null) {
			http.setAttribute("user", u);
			http.setAttribute("loggedIn", true);
			http.setAttribute("profile", p);
			if (p != null) {
				http.setAttribute("profileCreated", true);
			}
			else {
				http.setAttribute("profileCreated", false);
			}
			if (u.getId() == 1) {
				http.setAttribute("adminLoggedIn", true);
			}
			else {
				http.setAttribute("adminLoggedIn", false);
			}
			return "redirect:account.do";
		}
		http.setAttribute("loggedIn", false);
		return "WEB-INF/login.jsp";
	}
	
	@RequestMapping(path ="logout.do") 
	public String logoutAttempt(HttpSession http) {
		http.removeAttribute("user");
		http.setAttribute("loggedIn", false);
		return "redirect:login.do";
	}
	
	@RequestMapping(path = "register.do", method = RequestMethod.GET)
	public ModelAndView register(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new User());
		mv.setViewName("WEB-INF/register.jsp");
		session.setAttribute("adminLoggedIn", false);
		return mv;
	}
	
	@RequestMapping(path = "userDetails.do", method = RequestMethod.GET)
	public String getUser(@RequestParam("userId") int userId, RedirectAttributes redir, HttpSession session) {
		User user = udao.findUserById(userId);
		redir.addFlashAttribute("user", user);
		session.setAttribute("user", user);
		return "redirect:usercreated.do";
	}

	@RequestMapping(path = "usercreated.do", method = RequestMethod.GET)
	public ModelAndView userCreated(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/newUserDetails.jsp");
		session.setAttribute("profileCreated", false);
		session.setAttribute("loggedIn", true);
		return mv;
	}
	
	@RequestMapping(path = "registerUser.do", method = RequestMethod.POST)
	public String addUserDetails(User user, HttpSession session, RedirectAttributes redir) {
	    String pHash = md5.hashPassword(user.getPassword());
	    user.setPassword(pHash);
		User userNew = ldao.createUser(user);
		redir.addFlashAttribute("user", userNew);
		session.setAttribute("user", userNew);
		session.setAttribute("loggedIn", true);
		return "redirect:usercreated.do";
	}
	
	@RequestMapping(path = "updateUser.do", method = RequestMethod.GET)
	public ModelAndView updateUser(HttpSession session) {
		User current = getCurrentUserFromSession(session);
		ModelAndView mv = new ModelAndView();
		User user = udao.findUserById(current.getId());
		mv.addObject("userUpdate", user);
		mv.setViewName("WEB-INF/updateUser.jsp"); 
		mv.addObject("userUpdateDetail", new ProfileDTO());
		session.setAttribute("user", user);
		return mv;
	}

	@RequestMapping(path = "updateUserDetails.do", method = RequestMethod.POST)
	public ModelAndView updateUserDetails(HttpSession session, User user) {	    
		User current = getCurrentUserFromSession(session);
		ModelAndView mv = new ModelAndView();
		
		User userUpdated = udao.updateUser(user, current.getId());
		mv.addObject("userId", userUpdated.getId());
		mv.addObject("userUpdated", userUpdated);
		mv.setViewName("WEB-INF/updatedUserDetails.jsp");
		session.setAttribute("user", userUpdated);
		return mv;
	}
	
	private User getCurrentUserFromSession(HttpSession session) {
		User current = (User) session.getAttribute("user");
		return current;
	}
	

}
