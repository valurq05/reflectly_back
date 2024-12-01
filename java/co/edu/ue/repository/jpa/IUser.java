package co.edu.ue.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ue.entity.User;

public interface IUser extends JpaRepository<User, Integer> {

	User findByuseMail(String useMail);	
	Boolean existsByuseMail(String useMail);
}
