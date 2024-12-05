package co.edu.ue.repository.dao;

import java.util.List;

import co.edu.ue.entity.Person;

public interface IPersonRepositoryDao {

	List<Person> insertPerson(Person person);
	Person updatePerson(Person person);
	Person findIdPerson(int id);
	List<Person> listPersons();
	Boolean existsPerId(int perId);
}
