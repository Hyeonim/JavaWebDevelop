package com.yi.spring.service;

import com.yi.spring.entity.Dinning;
import com.yi.spring.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findByRestNo(Dinning restNo);

    void createEvent(Event event);

    Event findByEventNo(int eventNo);

    void updateEvent(Event event);

    void deleteEvent(Event event);

    List<Event> getNewEvents();
}
