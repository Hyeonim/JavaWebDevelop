package com.yi.spring.service;

import com.yi.spring.entity.Dinning;
import com.yi.spring.entity.Event;
import com.yi.spring.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> findByRestNo(Dinning restNo) {
        return eventRepository.findByRestNo(restNo);
    }

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event findByEventNo(int eventNo) {
        Optional<Event> optionalEvent = eventRepository.findById(eventNo);
        return optionalEvent.orElse(null);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getNewEvents() {

        return eventRepository.getNewEvents();
    }
}
