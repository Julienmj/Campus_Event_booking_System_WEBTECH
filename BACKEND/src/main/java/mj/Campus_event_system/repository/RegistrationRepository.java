package mj.Campus_event_system.repository;

import mj.Campus_event_system.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, String> {}
