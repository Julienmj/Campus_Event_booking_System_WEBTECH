package mj.Campus_event_system.service;

import mj.Campus_event_system.model.Registration;
import java.util.List;

public interface RegistrationService {
    Registration register(Registration registration);
    List<Registration> getAllRegistrations();
}
