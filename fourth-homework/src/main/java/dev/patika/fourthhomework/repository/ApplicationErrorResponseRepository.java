package dev.patika.fourthhomework.repository;

import dev.patika.fourthhomework.model.ApplicationErrorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationErrorResponseRepository extends JpaRepository<ApplicationErrorResponse,Integer> {
}
