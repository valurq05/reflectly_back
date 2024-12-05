package co.edu.ue.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.User;
import co.edu.ue.repository.jpa.IUser;

@Repository
public class UserRepositoryDao implements IUserRepositoryDao{

	@Autowired
	IUser userJPA;
	@Override
	public List<User> insertUser(User user) {
		userJPA.save(user);
		return listUser();
	}

	@Override
	public User updateUser(User User) {
		
		return userJPA.save(User);
	}

	@Override
	public User findIdUser(int id) {
		return userJPA.findById(id).orElseThrow( () -> new RuntimeException("No se encontro el usuario"));
	}

	@Override
	public List<User> listUser() {
	;
		return userJPA.findAll();
	}

	@Override
	public User findMailUser(String mail) {
		return userJPA.findByuseMail(mail);
	}

	@Override
	public Boolean existMailUser(String mail) {
		
		
		return userJPA.existsByuseMail(mail);
	}

	@Override
	public Boolean existIdUser(int id) {
		return userJPA.existsByuseId(id);
	}

}
