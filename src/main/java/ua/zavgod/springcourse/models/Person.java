package ua.zavgod.springcourse.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Column(name = "fio")
    private String fio;

    @Min(value = 1, message = "Year should be more than 0")
    @Max(value = 2023, message = "Year should be less than 2023")
    @NotNull(message = "Year should not be empty")
    @Column(name = "year")
    private int year;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "owner")
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> books;

    public Person() {

    }

    public Person(String fio, int year) {
        this.fio = fio;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
