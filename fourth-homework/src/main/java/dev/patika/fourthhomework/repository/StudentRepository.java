package dev.patika.fourthhomework.repository;

import dev.patika.fourthhomework.model.Student;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {


    Optional<Student> findByName(String name);
    void deleteByName(String name);
    boolean existsByNameAndBirthDate(String name,LocalDate birthDate);


}
