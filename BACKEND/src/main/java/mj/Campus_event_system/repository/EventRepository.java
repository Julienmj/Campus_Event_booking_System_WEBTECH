package mj.Campus_event_system.repository;

import mj.Campus_event_system.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {}
