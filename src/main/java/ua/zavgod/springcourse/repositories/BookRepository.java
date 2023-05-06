package ua.zavgod.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.zavgod.springcourse.models.Book;
import ua.zavgod.springcourse.models.Person;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(Person owner);

    List<Book> findByNameStartingWith(String query);

}
