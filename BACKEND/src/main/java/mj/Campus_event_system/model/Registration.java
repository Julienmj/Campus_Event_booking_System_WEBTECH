package mj.Campus_event_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String regId;

    private String name;
    private Long eventId;

    public Registration() {}

    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
}
