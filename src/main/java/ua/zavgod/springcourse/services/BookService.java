package ua.zavgod.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.zavgod.springcourse.models.Book;
import ua.zavgod.springcourse.models.Person;
import ua.zavgod.springcourse.repositories.BookRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(Sort.by("year"));
        }
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookToBeUpdated = bookRepository.findById(id).get();

        book.setId(id);
        book.setOwner(bookToBeUpdated.getOwner());

        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByOwner(Person owner) {
        return bookRepository.findByOwner(owner);
    }

    public Person getBookOwner(int id) {
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
                }
        );
    }

    @Transactional
    public void assign(int id, Person person) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(person);
                    book.setTakenAt(new Date());
                }
        );
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear) {
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        }
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> searchByTitle(String query) {
        return bookRepository.findByNameStartingWith(query);
    }
}
