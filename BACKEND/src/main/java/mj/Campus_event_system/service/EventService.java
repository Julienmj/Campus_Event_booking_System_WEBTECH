package mj.Campus_event_system.service;

import mj.Campus_event_system.model.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Long id);
    Event createEvent(Event event);
}
