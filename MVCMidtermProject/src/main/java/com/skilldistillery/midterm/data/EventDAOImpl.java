package com.skilldistillery.midterm.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.midterm.entities.Event;
import com.skilldistillery.midterm.entities.EventDTO;
import com.skilldistillery.midterm.entities.Interest;
import com.skilldistillery.midterm.entities.Location;

@Transactional
@Component
public class EventDAOImpl implements EventDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Event create(Event event) {
		em.persist(event);
		em.flush();

		return event;
	}

	@Override
	public Event update(int id, Event updatedEvent) {
		Event managed = em.find(Event.class, id);
		managed.setName(updatedEvent.getName());
		managed.setDescription(updatedEvent.getDescription());
		managed.setDate(updatedEvent.getDate());
		managed.setInterests(updatedEvent.getInterests());
		managed.setLocation(updatedEvent.getLocation());

		return managed;
	}

	@Override
	public void delete(int id) {
		Event managed = em.find(Event.class, id);
		em.remove(managed);

	}

	@Override
	public Event createEventAndLocation(EventDTO dto) {
		Event e = new Event();
		Location l = new Location();
		e.setName(dto.getName());
		e.setDescription(dto.getDescription());
		e.setDate(dto.getDate());
		e.addInterest(g);
		
		event.setLocation(location);
		em.persist(event);
		em.flush();

		return event;
	}

	@Override
	public List<Event> index() {
		String queryString = "SELECT e FROM Event e";
		List<Event> events = em.createQuery(queryString, Event.class).getResultList();
		return events;
	}

	@Override
	public Event getEventById(int id) {
		String query = "SELECT e FROM Event e JOIN FETCH e.interests WHERE e.id = :id";
		Event event = em.createQuery(query, Event.class).setParameter("id", id).getResultList().get(0);
		return event;
	}

	@Override
	public Event find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
