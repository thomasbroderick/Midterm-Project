package com.skilldistillery.midterm.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.midterm.entities.Profile;
import com.skilldistillery.midterm.entities.User;

@Transactional
@Component
public class UserDAOImpl implements UserDAO {
	
	 @PersistenceContext
	  private EntityManager em;
	 
	 public static void main(String[] args) {
		UserDAOImpl udao = new UserDAOImpl();
		udao.run();
	}

	private void run() {
//		System.out.println(em.find(User.class, 1));
	}

	@Override
	public User createUser(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public void deleteUser(User user) {
		user.setActive(false);
	}

	@Override
	public User updateUser(User user, int userId) {
		User managed = em.find(User.class, userId);
		managed.setAccess(user.getAccess());
		managed.setActive(user.getActive());
		managed.setEmail(user.getEmail());
		managed.setPassword(user.getPassword());
		managed.setUsername(user.getUsername());
		return managed;
	}
	
	
	@Override
	public Profile createProfile(Profile profile) {
		em.persist(profile);
		return profile;
	}
	
	@Override
	public Profile updateProfile(Profile profile, int profileId) {
		Profile managed = em.find(Profile.class, profileId);
		managed.setAboutMe(profile.getAboutMe());
		managed.setAge(profile.getAge());
		managed.setFirstName(profile.getFirstName());
		managed.setLastName(profile.getLastName());
		managed.setGender(profile.getGender());
		managed.setSexualOrientation(profile.getSexualOrientation());
		managed.setLocation(profile.getLocation());
		managed.setPictureUrl(profile.getPictureUrl());
		managed.setUserId(profile.getUserId());
		return managed;
	}

	@Override
	public User findUserById(int userId) {
		return em.find(User.class, userId);
	}

	@Override
	public Profile findProfileById(int profileId) {
		return em.find(Profile.class, profileId);
	}
	
	
}