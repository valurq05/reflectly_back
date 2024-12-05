package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.entity.Person;
import co.edu.ue.repository.dao.IPersonRepositoryDao;

@Service
public class PersonService implements IPersonService {

	@Autowired
	IPersonRepositoryDao personDao;
	@Override
	public List<Person> addPerson(Person person) {
		return personDao.insertPerson(person);
	}

	@Override
	public Person upPerson(Person person) {
		return personDao.updatePerson(person);
	}

	@Override
	public Person findByIdPerson(int id) {
		return personDao.findIdPerson(id);
	}

	@Override
	public List<Person> listAllPersons() {
		return personDao.listPersons();
	}

	@Override
	public Boolean existsByperId(int perId) {
		return personDao.existsPerId(perId);
	}

}
