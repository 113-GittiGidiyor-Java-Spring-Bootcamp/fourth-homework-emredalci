package dev.patika.fourthhomework.repository;

import dev.patika.fourthhomework.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    Optional<Instructor> findByName(String instructorName);
    void deleteByName(String instructorName);
    boolean existsByNameAndPhoneNumber(String instructorName,String phoneNumber);

}
