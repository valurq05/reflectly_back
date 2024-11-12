package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.Person;

public interface IPerson extends JpaRepository<Person, Integer> {
	

}
