package com.skilldistillery.midterm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.midterm.entities.Membership;
import com.skilldistillery.midterm.entities.Profile;
import com.skilldistillery.midterm.entities.User;

@Transactional
@Component
public class LoginDAOImpl implements LoginDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUserByUserNameAndPassword(String username, String password) {
	    User u = null;
		String query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password";
		String query2 = "SELECT u FROM User u";
		List<User> users = em.createQuery(query2, User.class).getResultList();
		u = em.createQuery(query, User.class).setParameter("username", username).setParameter("password", password)
				.getSingleResult();
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				u = user;
				break;
			}
		}
		return u;
	}
	
	@Override
	public User createUser(User user) {
		user.setMembership(em.find(Membership.class, 1));
		em.persist(user);
		return user;
	}
	
	@Override
	public User updateUser(User user, int userId) {
		user.setMembership(user.getMembership());
		User managed = em.find(User.class, userId);
		managed.setUsername(user.getUsername());
		managed.setPassword(user.getPassword());
		managed.setEmail(user.getEmail());
		managed.setAccess(user.getAccess());
		managed.setActive(user.getActive());
		return managed;
	}
}
