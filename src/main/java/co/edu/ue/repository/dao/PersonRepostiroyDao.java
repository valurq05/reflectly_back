package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Person;
import co.edu.ue.repository.jpa.IPerson;

@Repository
public class PersonRepostiroyDao implements IPersonRepositoryDao {

	@Autowired 
	IPerson personJpa;
	@Override
	public List<Person> insertPerson(Person person) {
		personJpa.save(person);
		return listPersons();
	}

	@Override
	public Person updatePerson(Person person) {
		return personJpa.save(person);
	}

	@Override
	public Person findIdPerson(int id) {
		return personJpa.findById(id).orElse(null);
	}

	@Override
	public List<Person> listPersons() {
		return personJpa.findAll();
	}

}
