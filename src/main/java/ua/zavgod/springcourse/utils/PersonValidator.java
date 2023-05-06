package ua.zavgod.springcourse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.zavgod.springcourse.models.Person;
import ua.zavgod.springcourse.services.PeopleService;

/**
 * @author Neil Alishev
 */
@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (peopleService.getPersonByFullName(person.getFio()).isPresent())
            errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
    }
}
