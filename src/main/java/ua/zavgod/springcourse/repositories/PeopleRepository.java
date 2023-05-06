package ua.zavgod.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.zavgod.springcourse.models.Book;
import ua.zavgod.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByFio(String fio);
}
