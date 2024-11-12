package co.edu.ue.service;

import java.util.List;

import co.edu.ue.entity.Person;

public interface IPersonService {

	List<Person> addPerson(Person person);
	Person upPerson(Person person);
	Person findByIdPerson(int id);
	List<Person> listAllPersons();
}
